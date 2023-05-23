package com.pensatocode.example.resources;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.pensatocode.example.core.Article;
import com.pensatocode.example.db.ArticleRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/articles")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleResource {
    private final int defaultSize;

    private final ArticleRepository articleRepository;

    public ArticleResource(int defaultSize, ArticleRepository articleRepository) {
        this.defaultSize = defaultSize;
        this.articleRepository = articleRepository;
        System.out.println("############## ArticleResource Constructor ##############");
    }

    @GET
    @Timed
    public List<Article> getArticles(@QueryParam("size") Optional<Integer> size) {
        return articleRepository.findAll(size.orElse(defaultSize));
    }

    @GET
    @Path("/{id}")
    @Metered
    public Article getById(@PathParam("id") Long id) {
        return articleRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
