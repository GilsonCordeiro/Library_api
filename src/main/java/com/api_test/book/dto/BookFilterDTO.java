package com.api_test.book.dto;


public class BookFilterDTO {
    private String title;
    private String author;
    private Long categoryId;
    private Integer year;
    private java.math.BigDecimal minPrice;
    private java.math.BigDecimal maxPrice;
    private Boolean ativo;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public java.math.BigDecimal getMinPrice() { return minPrice; }
    public void setMinPrice(java.math.BigDecimal minPrice) { this.minPrice = minPrice; }
    public java.math.BigDecimal getMaxPrice() { return maxPrice; }
    public void setMaxPrice(java.math.BigDecimal maxPrice) { this.maxPrice = maxPrice; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
