package com.api_test.book.dto;


import java.math.BigDecimal;

public class BookFormDTO {
    private Long id;
    private String title;
    private String author;
    private String text;
    private Integer yearPublication;
    private BigDecimal price;
    private String imagem;
    private Boolean ativo;
    private Long categoryId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Integer getYearPublication() { return yearPublication; }
    public void setYearPublication(Integer yearPublication) { this.yearPublication = yearPublication; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}
