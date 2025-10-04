package com.faculdade.pubsub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * DTO para categoria de quarto
 */
public class CategoryDto {
    
    private String id;
    private String name;
    
    @JsonProperty("sub_category")
    private SubCategoryDto subCategory;
    
    // Construtores
    public CategoryDto() {}
    
    public CategoryDto(String id, String name, SubCategoryDto subCategory) {
        this.id = id;
        this.name = name;
        this.subCategory = subCategory;
    }
    
    // Getters e Setters
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
    
    public SubCategoryDto getSubCategory() {
        return subCategory;
    }
    
    public void setSubCategory(SubCategoryDto subCategory) {
        this.subCategory = subCategory;
    }
    
    @Override
    public String toString() {
        return "CategoryDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", subCategory=" + subCategory +
                '}';
    }
    
    /**
     * DTO para subcategoria de quarto
     */
    public static class SubCategoryDto {
        private String id;
        private String name;
        
        public SubCategoryDto() {}
        
        public SubCategoryDto(String id, String name) {
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
        
        @Override
        public String toString() {
            return "SubCategoryDto{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
