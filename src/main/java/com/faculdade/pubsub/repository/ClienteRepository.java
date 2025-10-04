package com.faculdade.pubsub.repository;

import com.faculdade.pubsub.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository para entidade Cliente
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    /**
     * Busca cliente por email
     */
    Optional<Cliente> findByEmail(String email);
    
    /**
     * Busca cliente por documento
     */
    Optional<Cliente> findByDocument(String document);
    
    /**
     * Busca cliente por email ou documento
     */
    @Query("SELECT c FROM Cliente c WHERE c.email = :email OR c.document = :document")
    Optional<Cliente> findByEmailOrDocument(@Param("email") String email, @Param("document") String document);
    
    /**
     * Verifica se existe cliente com email
     */
    boolean existsByEmail(String email);
    
    /**
     * Verifica se existe cliente com documento
     */
    boolean existsByDocument(String document);
}
