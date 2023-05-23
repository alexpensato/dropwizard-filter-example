package com.pensatocode.example.db;

import com.pensatocode.example.client.ArticleBootstrap;
import com.pensatocode.example.core.Article;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArticleRepository {
    private final List<Article> articles;

    public ArticleRepository() {
        super();
        this.articles = ArticleBootstrap.initArticles();
    }

    public List<Article> findAll(int size) {
        return articles.stream()
                .limit(size)
                .collect(Collectors.toList());
    }

    public Optional<Article> findById(Long id) {
        return articles.stream()
                .filter(brand -> brand.getId().equals(id))
                .findFirst();
    }

}
