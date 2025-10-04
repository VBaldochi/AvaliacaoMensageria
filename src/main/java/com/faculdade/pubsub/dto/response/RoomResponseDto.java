package com.faculdade.pubsub.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de resposta para quarto na API
 */
public class RoomResponseDto {
    
    private Long id;
    
    @JsonProperty("room_number")
    private String roomNumber;
    
    @JsonProperty("daily_rate")
    private BigDecimal dailyRate;
    
    @JsonProperty("number_of_days")
    private Integer numberOfDays;
    
    @JsonProperty("checkin_date")
    private LocalDate checkinDate;
    
    @JsonProperty("checkout_date")
    private LocalDate checkoutDate;
    
    private CategoryResponseDto category;
    private String status;
    private Integer guests;
    
    @JsonProperty("breakfast_included")
    private Boolean breakfastIncluded;
    
    @JsonProperty("total_value")
    private BigDecimal totalValue;
    
    // Construtores
    public RoomResponseDto() {}
    
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
    
    public LocalDate getCheckinDate() {
        return checkinDate;
    }
    
    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }
    
    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }
    
    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
    
    public CategoryResponseDto getCategory() {
        return category;
    }
    
    public void setCategory(CategoryResponseDto category) {
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
    
    public BigDecimal getTotalValue() {
        return totalValue;
    }
    
    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
    
    /**
     * DTO para categoria na resposta
     */
    public static class CategoryResponseDto {
        private String id;
        private String name;
        
        @JsonProperty("sub_category")
        private SubCategoryResponseDto subCategory;
        
        public CategoryResponseDto() {}
        
        public CategoryResponseDto(String id, String name, SubCategoryResponseDto subCategory) {
            this.id = id;
            this.name = name;
            this.subCategory = subCategory;
        }
        
        public String getId() {
            return id;
        }
        
        public void setId(String id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public SubCategoryResponseDto getSubCategory() {
            return subCategory;
        }
        
        public void setSubCategory(SubCategoryResponseDto subCategory) {
            this.subCategory = subCategory;
        }
        
        /**
         * DTO para subcategoria na resposta
         */
        public static class SubCategoryResponseDto {
            private String id;
            private String name;
            
            public SubCategoryResponseDto() {}
            
            public SubCategoryResponseDto(String id, String name) {
                this.id = id;
                this.name = name;
            }
            
            public String getId() {
                return id;
            }
            
            public void setId(String id) {
                this.id = id;
            }
            
            public String getName() {
                return name;
            }
            
            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
