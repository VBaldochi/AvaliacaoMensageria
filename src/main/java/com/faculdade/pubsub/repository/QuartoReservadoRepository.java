package com.faculdade.pubsub.repository;

import com.faculdade.pubsub.entity.QuartoReservado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository para entidade QuartoReservado
 */
@Repository
public interface QuartoReservadoRepository extends JpaRepository<QuartoReservado, Long> {
    
    /**
     * Busca quartos por reserva ID
     */
    @Query("SELECT q FROM QuartoReservado q WHERE q.reserva.id = :reservaId")
    List<QuartoReservado> findByReservaId(@Param("reservaId") Long reservaId);
    
    /**
     * Busca quartos por room ID
     */
    List<QuartoReservado> findByRoomId(Long roomId);
    
    /**
     * Busca quartos por número do quarto
     */
    List<QuartoReservado> findByRoomNumber(String roomNumber);
    
    /**
     * Busca quartos por UUID da reserva
     */
    @Query("SELECT q FROM QuartoReservado q WHERE q.reserva.uuid = :reservaUuid")
    List<QuartoReservado> findByReservaUuid(@Param("reservaUuid") String reservaUuid);
}
