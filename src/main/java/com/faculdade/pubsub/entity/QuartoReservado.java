package com.faculdade.pubsub.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidade QuartoReservado para sistema de hotelaria
 */
@Entity
@Table(name = "quarto_reservado")
public class QuartoReservado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id", nullable = false)
    private Reserva reserva;
    
    @Column(name = "room_id", nullable = false)
    private Long roomId;
    
    @Column(name = "room_number", nullable = false)
    private String roomNumber;
    
    @Column(name = "daily_rate", precision = 10, scale = 2, nullable = false)
    private BigDecimal dailyRate;
    
    @Column(name = "number_of_days", nullable = false)
    private Integer numberOfDays;
    
    @Column(name = "checkin_date", nullable = false)
    private LocalDate checkinDate;
    
    @Column(name = "checkout_date", nullable = false)
    private LocalDate checkoutDate;
    
    @Column(name = "category_id")
    private String categoryId;
    
    @Column(name = "category_name")
    private String categoryName;
    
    @Column(name = "sub_category_id")
    private String subCategoryId;
    
    @Column(name = "sub_category_name")
    private String subCategoryName;
    
    @Column(nullable = false)
    private String status;
    
    @Column(nullable = false)
    private Integer guests;
    
    @Column(name = "breakfast_included", nullable = false)
    private Boolean breakfastIncluded;
    
    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;
    
    // Construtores
    public QuartoReservado() {}
    
    public QuartoReservado(Reserva reserva, Long roomId, String roomNumber, 
                          BigDecimal dailyRate, Integer numberOfDays) {
        this.reserva = reserva;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.dailyRate = dailyRate;
        this.numberOfDays = numberOfDays;
        this.valorTotal = dailyRate.multiply(BigDecimal.valueOf(numberOfDays));
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Reserva getReserva() {
        return reserva;
    }
    
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    
    public Long getRoomId() {
        return roomId;
    }
    
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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
        this.calculateValorTotal();
    }
    
    public Integer getNumberOfDays() {
        return numberOfDays;
    }
    
    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
        this.calculateValorTotal();
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
    
    public String getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public String getSubCategoryId() {
        return subCategoryId;
    }
    
    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
    
    public String getSubCategoryName() {
        return subCategoryName;
    }
    
    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
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
    
    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    /**
     * Calcula o valor total do quarto (diária * número de dias)
     */
    public void calculateValorTotal() {
        if (dailyRate != null && numberOfDays != null) {
            this.valorTotal = dailyRate.multiply(BigDecimal.valueOf(numberOfDays));
        }
    }
    
    @PrePersist
    @PreUpdate
    public void prePersist() {
        this.calculateValorTotal();
    }
    
    @Override
    public String toString() {
        return "QuartoReservado{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", dailyRate=" + dailyRate +
                ", numberOfDays=" + numberOfDays +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
