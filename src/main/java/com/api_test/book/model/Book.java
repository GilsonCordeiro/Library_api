package com.api_test.book.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_book")
public class Book implements Serializable {
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String name;
    private String text;

    public Book() {
    }

    public Book(Long id, String title, String name, String text) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.text = text;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
