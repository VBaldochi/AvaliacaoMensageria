package com.faculdade.pubsub.repository;

import com.faculdade.pubsub.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para entidade Reserva
 */
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    /**
     * Busca reserva por UUID
     */
    Optional<Reserva> findByUuid(String uuid);
    
    /**
     * Busca reservas por cliente ID
     */
    @Query("SELECT r FROM Reserva r WHERE r.cliente.id = :clienteId")
    List<Reserva> findByClienteId(@Param("clienteId") Long clienteId);
    
    /**
     * Busca reservas por hotel ID
     */
    List<Reserva> findByHotelId(Long hotelId);
    
    /**
     * Busca reservas que contenham um quarto específico
     */
    @Query("SELECT DISTINCT r FROM Reserva r JOIN r.quartos q WHERE q.roomId = :roomId")
    List<Reserva> findByRoomId(@Param("roomId") Long roomId);
    
    /**
     * Busca reservas com filtros opcionais
     */
    @Query("SELECT DISTINCT r FROM Reserva r LEFT JOIN r.quartos q WHERE " +
           "(:uuid IS NULL OR r.uuid = :uuid) AND " +
           "(:clienteId IS NULL OR r.cliente.id = :clienteId) AND " +
           "(:hotelId IS NULL OR r.hotelId = :hotelId) AND " +
           "(:roomId IS NULL OR q.roomId = :roomId)")
    List<Reserva> findWithFilters(@Param("uuid") String uuid,
                                 @Param("clienteId") Long clienteId,
                                 @Param("hotelId") Long hotelId,
                                 @Param("roomId") Long roomId);
    
    /**
     * Verifica se existe reserva com UUID
     */
    boolean existsByUuid(String uuid);
}
