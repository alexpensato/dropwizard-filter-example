package com.pensatocode.example.core;

public class Article {
    private final Long id;
    private final String title;

    public Article(Long id, String title) {
        super();
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}