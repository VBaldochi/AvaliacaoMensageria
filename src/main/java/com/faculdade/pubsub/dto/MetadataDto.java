package com.faculdade.pubsub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO para metadados no payload da reserva
 */
public class MetadataDto {
    
    private String source;
    
    @JsonProperty("user_agent")
    private String userAgent;
    
    @JsonProperty("ip_address")
    private String ipAddress;
    
    // Construtores
    public MetadataDto() {}
    
    public MetadataDto(String source, String userAgent, String ipAddress) {
        this.source = source;
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
    }
    
    // Getters e Setters
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
    
    @Override
    public String toString() {
        return "MetadataDto{" +
                "source='" + source + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
