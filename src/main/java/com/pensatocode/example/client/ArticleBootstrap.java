package com.pensatocode.example.client;

import com.pensatocode.example.core.Article;

import java.util.List;

public final class ArticleBootstrap {
    private ArticleBootstrap() {
        // Nothing to do
    }

    public static List<Article> initArticles() {
        var articles = new java.util.ArrayList<Article>();
        articles.add(new Article(1L, "Article 1"));
        articles.add(new Article(2L, "Article 2"));
        articles.add(new Article(3L, "Article 3"));
        articles.add(new Article(4L, "Article 4"));
        articles.add(new Article(5L, "Article 5"));
        return articles;
    }
}
