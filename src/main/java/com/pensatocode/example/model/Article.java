package com.pensatocode.example.model;

public class Article {
    public static final Article EMPTY = new Article(0L, "");
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
