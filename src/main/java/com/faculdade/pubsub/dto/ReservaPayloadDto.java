package com.faculdade.pubsub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * DTO principal para o payload da reserva recebido via Pub/Sub
 */
public class ReservaPayloadDto {
    
    private String uuid;
    
    @JsonProperty("created_at")
    private String createdAt;
    
    private String type;
    private CustomerDto customer;
    private HotelDto hotel;
    private List<RoomDto> rooms;
    private PaymentDto payment;
    private MetadataDto metadata;
    
    // Construtores
    public ReservaPayloadDto() {}
    
    // Getters e Setters
    public String getUuid() {
        return uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public String getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public CustomerDto getCustomer() {
        return customer;
    }
    
    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
    
    public HotelDto getHotel() {
        return hotel;
    }
    
    public void setHotel(HotelDto hotel) {
        this.hotel = hotel;
    }
    
    public List<RoomDto> getRooms() {
        return rooms;
    }
    
    public void setRooms(List<RoomDto> rooms) {
        this.rooms = rooms;
    }
    
    public PaymentDto getPayment() {
        return payment;
    }
    
    public void setPayment(PaymentDto payment) {
        this.payment = payment;
    }
    
    public MetadataDto getMetadata() {
        return metadata;
    }
    
    public void setMetadata(MetadataDto metadata) {
        this.metadata = metadata;
    }
    
    @Override
    public String toString() {
        return "ReservaPayloadDto{" +
                "uuid='" + uuid + '\'' +
                ", type='" + type + '\'' +
                ", customer=" + customer +
                ", hotel=" + hotel +
                ", rooms=" + (rooms != null ? rooms.size() : 0) + " rooms" +
                '}';
    }
}
