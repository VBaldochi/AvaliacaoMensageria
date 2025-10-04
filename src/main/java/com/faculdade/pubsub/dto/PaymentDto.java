package com.faculdade.pubsub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO para dados de pagamento no payload da reserva
 */
public class PaymentDto {
    
    private String method;
    private String status;
    
    @JsonProperty("transaction_id")
    private String transactionId;
    
    // Construtores
    public PaymentDto() {}
    
    public PaymentDto(String method, String status, String transactionId) {
        this.method = method;
        this.status = status;
        this.transactionId = transactionId;
    }
    
    // Getters e Setters
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
    
    @Override
    public String toString() {
        return "PaymentDto{" +
                "method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
