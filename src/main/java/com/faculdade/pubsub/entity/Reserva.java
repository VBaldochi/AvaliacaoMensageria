package com.faculdade.pubsub.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidade Reserva para sistema de hotelaria
 */
@Entity
@Table(name = "reserva")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String uuid;
    
    @Column(nullable = false)
    private String type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @Column(name = "hotel_id", nullable = false)
    private Long hotelId;
    
    @Column(name = "hotel_name", nullable = false)
    private String hotelName;
    
    @Column(name = "hotel_city")
    private String hotelCity;
    
    @Column(name = "hotel_state")
    private String hotelState;
    
    @Column(name = "payment_method")
    private String paymentMethod;
    
    @Column(name = "payment_status")
    private String paymentStatus;
    
    @Column(name = "transaction_id")
    private String transactionId;
    
    @Column(name = "metadata_source")
    private String metadataSource;
    
    @Column(name = "metadata_user_agent")
    private String metadataUserAgent;
    
    @Column(name = "metadata_ip_address")
    private String metadataIpAddress;
    
    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "indexed_at", nullable = false)
    private LocalDateTime indexedAt;
    
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuartoReservado> quartos;
    
    // Construtores
    public Reserva() {
        this.indexedAt = LocalDateTime.now();
    }
    
    public Reserva(String uuid, String type, Cliente cliente) {
        this();
        this.uuid = uuid;
        this.type = type;
        this.cliente = cliente;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUuid() {
        return uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Long getHotelId() {
        return hotelId;
    }
    
    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
    
    public String getHotelName() {
        return hotelName;
    }
    
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    
    public String getHotelCity() {
        return hotelCity;
    }
    
    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }
    
    public String getHotelState() {
        return hotelState;
    }
    
    public void setHotelState(String hotelState) {
        this.hotelState = hotelState;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getPaymentStatus() {
        return paymentStatus;
    }
    
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getMetadataSource() {
        return metadataSource;
    }
    
    public void setMetadataSource(String metadataSource) {
        this.metadataSource = metadataSource;
    }
    
    public String getMetadataUserAgent() {
        return metadataUserAgent;
    }
    
    public void setMetadataUserAgent(String metadataUserAgent) {
        this.metadataUserAgent = metadataUserAgent;
    }
    
    public String getMetadataIpAddress() {
        return metadataIpAddress;
    }
    
    public void setMetadataIpAddress(String metadataIpAddress) {
        this.metadataIpAddress = metadataIpAddress;
    }
    
    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getIndexedAt() {
        return indexedAt;
    }
    
    public void setIndexedAt(LocalDateTime indexedAt) {
        this.indexedAt = indexedAt;
    }
    
    public List<QuartoReservado> getQuartos() {
        return quartos;
    }
    
    public void setQuartos(List<QuartoReservado> quartos) {
        this.quartos = quartos;
    }
    
    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", type='" + type + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
