package com.api_test.book.dto;


public class CategoryGridDTO {
    private Long id;
    private String name;
    private String description;
    private Boolean isActive;

    // Explicit getters/setters for Lombok issues
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
