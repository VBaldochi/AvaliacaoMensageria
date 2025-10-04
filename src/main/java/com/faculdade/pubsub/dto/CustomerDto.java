package com.faculdade.pubsub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO para dados do cliente no payload da reserva
 */
public class CustomerDto {
    
    private Long id;
    private String name;
    private String email;
    private String document;
    
    // Construtores
    public CustomerDto() {}
    
    public CustomerDto(Long id, String name, String email, String document) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
    }
    
    // Getters e Setters
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
    
    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", document='" + document + '\'' +
                '}';
    }
}
