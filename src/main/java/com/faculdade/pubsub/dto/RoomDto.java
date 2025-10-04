package com.faculdade.pubsub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * DTO para dados de quarto no payload da reserva
 */
public class RoomDto {
    
    private Long id;
    
    @JsonProperty("room_number")
    private String roomNumber;
    
    @JsonProperty("daily_rate")
    private BigDecimal dailyRate;
    
    @JsonProperty("number_of_days")
    private Integer numberOfDays;
    
    @JsonProperty("checkin_date")
    private String checkinDate;
    
    @JsonProperty("checkout_date")
    private String checkoutDate;
    
    private CategoryDto category;
    private String status;
    private Integer guests;
    
    @JsonProperty("breakfast_included")
    private Boolean breakfastIncluded;
    
    // Construtores
    public RoomDto() {}
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getRoomNumber() {
        return roomNumber;
    }
    
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public BigDecimal getDailyRate() {
        return dailyRate;
    }
    
    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }
    
    public Integer getNumberOfDays() {
        return numberOfDays;
    }
    
    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
    
    public String getCheckinDate() {
        return checkinDate;
    }
    
    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }
    
    public String getCheckoutDate() {
        return checkoutDate;
    }
    
    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
    
    public CategoryDto getCategory() {
        return category;
    }
    
    public void setCategory(CategoryDto category) {
        this.category = category;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Integer getGuests() {
        return guests;
    }
    
    public void setGuests(Integer guests) {
        this.guests = guests;
    }
    
    public Boolean getBreakfastIncluded() {
        return breakfastIncluded;
    }
    
    public void setBreakfastIncluded(Boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }
    
    @Override
    public String toString() {
        return "RoomDto{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", dailyRate=" + dailyRate +
                ", numberOfDays=" + numberOfDays +
                ", status='" + status + '\'' +
                '}';
    }
}
