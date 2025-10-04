package com.faculdade.pubsub.controller;

import com.faculdade.pubsub.dto.response.ReservaResponseDto;
import com.faculdade.pubsub.dto.response.RoomResponseDto;
import com.faculdade.pubsub.entity.QuartoReservado;
import com.faculdade.pubsub.entity.Reserva;
import com.faculdade.pubsub.repository.ReservaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para consulta de reservas
 */
@RestController
public class ReservaController {
    
    private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);
    
    @Autowired
    private ReservaRepository reservaRepository;
    
    /**
     * Endpoint GET /reserves para consultar reservas com filtros
     * 
     * @param uuid UUID da reserva
     * @param clienteId ID do cliente
     * @param hotelId ID do hotel  
     * @param roomId ID do quarto
     * @return Lista de reservas encontradas
     */
    @GetMapping("/reserves")
    public ResponseEntity<List<ReservaResponseDto>> getReservas(
            @RequestParam(required = false) String uuid,
            @RequestParam(name = "cliente_id", required = false) Long clienteId,
            @RequestParam(name = "hotel_id", required = false) Long hotelId,
            @RequestParam(name = "room_id", required = false) Long roomId) {
        
        logger.info("Consulta de reservas - UUID: {}, Cliente ID: {}, Hotel ID: {}, Room ID: {}", 
                   uuid, clienteId, hotelId, roomId);
        
        try {
            // Buscar reservas com filtros
            List<Reserva> reservas = reservaRepository.findWithFilters(uuid, clienteId, hotelId, roomId);
            
            // Converter para DTOs de resposta
            List<ReservaResponseDto> response = reservas.stream()
                    .map(this::convertToResponseDto)
                    .collect(Collectors.toList());
            
            logger.info("Encontradas {} reservas", response.size());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Erro ao consultar reservas: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Endpoint para consulta por UUID específico
     */
    @GetMapping("/reserves/{uuid}")
    public ResponseEntity<ReservaResponseDto> getReservaPorUuid(@PathVariable String uuid) {
        logger.info("Consulta de reserva por UUID: {}", uuid);
        
        try {
            return reservaRepository.findByUuid(uuid)
                    .map(this::convertToResponseDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            logger.error("Erro ao consultar reserva por UUID: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Converte entidade Reserva para DTO de resposta
     */
    private ReservaResponseDto convertToResponseDto(Reserva reserva) {
        ReservaResponseDto dto = new ReservaResponseDto();
        
        // Dados básicos da reserva
        dto.setUuid(reserva.getUuid());
        dto.setCreatedAt(reserva.getCreatedAt());
        dto.setType(reserva.getType());
        dto.setTotalValue(reserva.getValorTotal());
        
        // Cliente
        if (reserva.getCliente() != null) {
            ReservaResponseDto.CustomerResponseDto customer = new ReservaResponseDto.CustomerResponseDto();
            customer.setId(reserva.getCliente().getId());
            customer.setName(reserva.getCliente().getName());
            customer.setEmail(reserva.getCliente().getEmail());
            customer.setDocument(reserva.getCliente().getDocument());
            dto.setCustomer(customer);
        }
        
        // Hotel
        ReservaResponseDto.HotelResponseDto hotel = new ReservaResponseDto.HotelResponseDto();
        hotel.setId(reserva.getHotelId());
        hotel.setName(reserva.getHotelName());
        hotel.setCity(reserva.getHotelCity());
        hotel.setState(reserva.getHotelState());
        dto.setHotel(hotel);
        
        // Quartos
        if (reserva.getQuartos() != null && !reserva.getQuartos().isEmpty()) {
            List<RoomResponseDto> rooms = reserva.getQuartos().stream()
                    .map(this::convertQuartoToDto)
                    .collect(Collectors.toList());
            dto.setRooms(rooms);
        }
        
        // Pagamento
        ReservaResponseDto.PaymentResponseDto payment = new ReservaResponseDto.PaymentResponseDto();
        payment.setMethod(reserva.getPaymentMethod());
        payment.setStatus(reserva.getPaymentStatus());
        payment.setTransactionId(reserva.getTransactionId());
        dto.setPayment(payment);
        
        // Metadados
        ReservaResponseDto.MetadataResponseDto metadata = new ReservaResponseDto.MetadataResponseDto();
        metadata.setSource(reserva.getMetadataSource());
        metadata.setUserAgent(reserva.getMetadataUserAgent());
        metadata.setIpAddress(reserva.getMetadataIpAddress());
        dto.setMetadata(metadata);
        
        return dto;
    }
    
    /**
     * Converte QuartoReservado para RoomResponseDto
     */
    private RoomResponseDto convertQuartoToDto(QuartoReservado quarto) {
        RoomResponseDto roomDto = new RoomResponseDto();
        
        roomDto.setId(quarto.getRoomId());
        roomDto.setRoomNumber(quarto.getRoomNumber());
        roomDto.setDailyRate(quarto.getDailyRate());
        roomDto.setNumberOfDays(quarto.getNumberOfDays());
        roomDto.setCheckinDate(quarto.getCheckinDate());
        roomDto.setCheckoutDate(quarto.getCheckoutDate());
        roomDto.setStatus(quarto.getStatus());
        roomDto.setGuests(quarto.getGuests());
        roomDto.setBreakfastIncluded(quarto.getBreakfastIncluded());
        roomDto.setTotalValue(quarto.getValorTotal());
        
        // Categoria
        if (quarto.getCategoryId() != null) {
            RoomResponseDto.CategoryResponseDto category = new RoomResponseDto.CategoryResponseDto();
            category.setId(quarto.getCategoryId());
            category.setName(quarto.getCategoryName());
            
            // Subcategoria
            if (quarto.getSubCategoryId() != null) {
                RoomResponseDto.CategoryResponseDto.SubCategoryResponseDto subCategory = 
                    new RoomResponseDto.CategoryResponseDto.SubCategoryResponseDto();
                subCategory.setId(quarto.getSubCategoryId());
                subCategory.setName(quarto.getSubCategoryName());
                category.setSubCategory(subCategory);
            }
            
            roomDto.setCategory(category);
        }
        
        return roomDto;
    }
}
