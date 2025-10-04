package com.faculdade.pubsub.service;

import com.faculdade.pubsub.dto.ReservaPayloadDto;
import com.faculdade.pubsub.dto.RoomDto;
import com.faculdade.pubsub.entity.Cliente;
import com.faculdade.pubsub.entity.QuartoReservado;
import com.faculdade.pubsub.entity.Reserva;
import com.faculdade.pubsub.repository.ClienteRepository;
import com.faculdade.pubsub.repository.ReservaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Service para processar reservas recebidas via Pub/Sub
 */
@Service
public class ReservaService {
    
    private static final Logger logger = LoggerFactory.getLogger(ReservaService.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ReservaRepository reservaRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    /**
     * Processa o JSON de reserva recebido via Pub/Sub
     */
    @Transactional
    public Reserva processarReserva(String jsonMessage) {
        try {
            logger.info("Processando mensagem de reserva: {}", jsonMessage);
            
            // Converter JSON para DTO
            ReservaPayloadDto payload = objectMapper.readValue(jsonMessage, ReservaPayloadDto.class);
            logger.info("Payload convertido: {}", payload);
            
            // Verificar se a reserva já existe
            if (reservaRepository.existsByUuid(payload.getUuid())) {
                logger.warn("Reserva com UUID {} já existe. Ignorando.", payload.getUuid());
                return reservaRepository.findByUuid(payload.getUuid()).orElse(null);
            }
            
            // Buscar ou criar cliente
            Cliente cliente = buscarOuCriarCliente(payload.getCustomer());
            
            // Criar reserva
            Reserva reserva = criarReserva(payload, cliente);
            
            // Criar quartos reservados
            List<QuartoReservado> quartos = criarQuartosReservados(payload.getRooms(), reserva);
            reserva.setQuartos(quartos);
            
            // Calcular valor total
            BigDecimal valorTotal = calcularValorTotal(quartos);
            reserva.setValorTotal(valorTotal);
            
            // Salvar reserva
            reserva = reservaRepository.save(reserva);
            logger.info("Reserva salva com sucesso: {}", reserva);
            
            return reserva;
            
        } catch (Exception e) {
            logger.error("Erro ao processar reserva: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao processar reserva", e);
        }
    }
    
    /**
     * Busca cliente existente ou cria novo
     */
    private Cliente buscarOuCriarCliente(com.faculdade.pubsub.dto.CustomerDto customerDto) {
        return clienteRepository.findByEmailOrDocument(
                customerDto.getEmail(), 
                customerDto.getDocument()
        ).orElseGet(() -> {
            Cliente novoCliente = new Cliente();
            novoCliente.setName(customerDto.getName());
            novoCliente.setEmail(customerDto.getEmail());
            novoCliente.setDocument(customerDto.getDocument());
            
            logger.info("Criando novo cliente: {}", novoCliente);
            return clienteRepository.save(novoCliente);
        });
    }
    
    /**
     * Cria entidade Reserva a partir do payload
     */
    private Reserva criarReserva(ReservaPayloadDto payload, Cliente cliente) {
        Reserva reserva = new Reserva();
        reserva.setUuid(payload.getUuid());
        reserva.setType(payload.getType());
        reserva.setCliente(cliente);
        
        // Dados do hotel
        if (payload.getHotel() != null) {
            reserva.setHotelId(payload.getHotel().getId());
            reserva.setHotelName(payload.getHotel().getName());
            reserva.setHotelCity(payload.getHotel().getCity());
            reserva.setHotelState(payload.getHotel().getState());
        }
        
        // Dados de pagamento
        if (payload.getPayment() != null) {
            reserva.setPaymentMethod(payload.getPayment().getMethod());
            reserva.setPaymentStatus(payload.getPayment().getStatus());
            reserva.setTransactionId(payload.getPayment().getTransactionId());
        }
        
        // Metadados
        if (payload.getMetadata() != null) {
            reserva.setMetadataSource(payload.getMetadata().getSource());
            reserva.setMetadataUserAgent(payload.getMetadata().getUserAgent());
            reserva.setMetadataIpAddress(payload.getMetadata().getIpAddress());
        }
        
        // Data de criação
        if (payload.getCreatedAt() != null) {
            try {
                reserva.setCreatedAt(LocalDateTime.parse(payload.getCreatedAt(), DATETIME_FORMATTER));
            } catch (Exception e) {
                logger.warn("Erro ao parsear created_at: {}. Usando timestamp atual.", payload.getCreatedAt());
                reserva.setCreatedAt(LocalDateTime.now());
            }
        } else {
            reserva.setCreatedAt(LocalDateTime.now());
        }
        
        return reserva;
    }
    
    /**
     * Cria lista de quartos reservados
     */
    private List<QuartoReservado> criarQuartosReservados(List<RoomDto> roomsDto, Reserva reserva) {
        List<QuartoReservado> quartos = new ArrayList<>();
        
        if (roomsDto != null) {
            for (RoomDto roomDto : roomsDto) {
                QuartoReservado quarto = new QuartoReservado();
                quarto.setReserva(reserva);
                quarto.setRoomId(roomDto.getId());
                quarto.setRoomNumber(roomDto.getRoomNumber());
                quarto.setDailyRate(roomDto.getDailyRate());
                quarto.setNumberOfDays(roomDto.getNumberOfDays());
                quarto.setStatus(roomDto.getStatus());
                quarto.setGuests(roomDto.getGuests());
                quarto.setBreakfastIncluded(roomDto.getBreakfastIncluded());
                
                // Datas de check-in e check-out
                if (roomDto.getCheckinDate() != null) {
                    quarto.setCheckinDate(LocalDate.parse(roomDto.getCheckinDate(), DATE_FORMATTER));
                }
                if (roomDto.getCheckoutDate() != null) {
                    quarto.setCheckoutDate(LocalDate.parse(roomDto.getCheckoutDate(), DATE_FORMATTER));
                }
                
                // Categoria
                if (roomDto.getCategory() != null) {
                    quarto.setCategoryId(roomDto.getCategory().getId());
                    quarto.setCategoryName(roomDto.getCategory().getName());
                    
                    if (roomDto.getCategory().getSubCategory() != null) {
                        quarto.setSubCategoryId(roomDto.getCategory().getSubCategory().getId());
                        quarto.setSubCategoryName(roomDto.getCategory().getSubCategory().getName());
                    }
                }
                
                // Calcular valor total do quarto
                quarto.calculateValorTotal();
                
                quartos.add(quarto);
            }
        }
        
        return quartos;
    }
    
    /**
     * Calcula valor total da reserva
     */
    private BigDecimal calcularValorTotal(List<QuartoReservado> quartos) {
        return quartos.stream()
                .map(QuartoReservado::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
