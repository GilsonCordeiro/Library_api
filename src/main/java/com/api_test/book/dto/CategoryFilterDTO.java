package com.api_test.book.dto;


public class CategoryFilterDTO {
    private String name;
    private Boolean isActive;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
