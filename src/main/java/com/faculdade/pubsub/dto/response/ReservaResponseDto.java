package com.faculdade.pubsub.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO de resposta para reserva na API conforme especificação
 */
public class ReservaResponseDto {
    
    private String uuid;
    
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    
    private String type;
    private CustomerResponseDto customer;
    private HotelResponseDto hotel;
    private List<RoomResponseDto> rooms;
    private PaymentResponseDto payment;
    private MetadataResponseDto metadata;
    
    @JsonProperty("total_value")
    private BigDecimal totalValue;
    
    // Construtores
    public ReservaResponseDto() {}
    
    // Getters e Setters
    public String getUuid() {
        return uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public CustomerResponseDto getCustomer() {
        return customer;
    }
    
    public void setCustomer(CustomerResponseDto customer) {
        this.customer = customer;
    }
    
    public HotelResponseDto getHotel() {
        return hotel;
    }
    
    public void setHotel(HotelResponseDto hotel) {
        this.hotel = hotel;
    }
    
    public List<RoomResponseDto> getRooms() {
        return rooms;
    }
    
    public void setRooms(List<RoomResponseDto> rooms) {
        this.rooms = rooms;
    }
    
    public PaymentResponseDto getPayment() {
        return payment;
    }
    
    public void setPayment(PaymentResponseDto payment) {
        this.payment = payment;
    }
    
    public MetadataResponseDto getMetadata() {
        return metadata;
    }
    
    public void setMetadata(MetadataResponseDto metadata) {
        this.metadata = metadata;
    }
    
    public BigDecimal getTotalValue() {
        return totalValue;
    }
    
    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
    
    /**
     * DTO para cliente na resposta
     */
    public static class CustomerResponseDto {
        private Long id;
        private String name;
        private String email;
        private String document;
        
        public CustomerResponseDto() {}
        
        public CustomerResponseDto(Long id, String name, String email, String document) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.document = document;
        }
        
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
        
        public String getDocument() {
            return document;
        }
        
        public void setDocument(String document) {
            this.document = document;
        }
    }
    
    /**
     * DTO para hotel na resposta
     */
    public static class HotelResponseDto {
        private Long id;
        private String name;
        private String city;
        private String state;
        
        public HotelResponseDto() {}
        
        public HotelResponseDto(Long id, String name, String city, String state) {
            this.id = id;
            this.name = name;
            this.city = city;
            this.state = state;
        }
        
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getCity() {
            return city;
        }
        
        public void setCity(String city) {
            this.city = city;
        }
        
        public String getState() {
            return state;
        }
        
        public void setState(String state) {
            this.state = state;
        }
    }
    
    /**
     * DTO para pagamento na resposta
     */
    public static class PaymentResponseDto {
        private String method;
        private String status;
        
        @JsonProperty("transaction_id")
        private String transactionId;
        
        public PaymentResponseDto() {}
        
        public PaymentResponseDto(String method, String status, String transactionId) {
            this.method = method;
            this.status = status;
            this.transactionId = transactionId;
        }
        
        public String getMethod() {
            return method;
        }
        
        public void setMethod(String method) {
            this.method = method;
        }
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
        
        public String getTransactionId() {
            return transactionId;
        }
        
        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }
    }
    
    /**
     * DTO para metadados na resposta
     */
    public static class MetadataResponseDto {
        private String source;
        
        @JsonProperty("user_agent")
        private String userAgent;
        
        @JsonProperty("ip_address")
        private String ipAddress;
        
        public MetadataResponseDto() {}
        
        public MetadataResponseDto(String source, String userAgent, String ipAddress) {
            this.source = source;
            this.userAgent = userAgent;
            this.ipAddress = ipAddress;
        }
        
        public String getSource() {
            return source;
        }
        
        public void setSource(String source) {
            this.source = source;
        }
        
        public String getUserAgent() {
            return userAgent;
        }
        
        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }
        
        public String getIpAddress() {
            return ipAddress;
        }
        
        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }
    }
}
