package com.pensatocode.example.resources;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.pensatocode.example.api.Scope;
import com.pensatocode.example.core.Article;
import com.pensatocode.example.db.ArticleRepository;
import com.pensatocode.example.filters.ScopeAllowed;
import com.pensatocode.example.filters.ScopeRequired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/articles")
@Produces(MediaType.APPLICATION_JSON)
@ScopeRequired
public class ArticleResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleResource.class);

    private final int defaultSize;

    private final ArticleRepository articleRepository;

    public ArticleResource(int defaultSize, ArticleRepository articleRepository) {
        this.defaultSize = defaultSize;
        this.articleRepository = articleRepository;
        LOGGER.info("############## ArticleResource Constructor ##############");
    }

    @GET
    @Timed
    @ScopeAllowed()
    public List<Article> getArticles(@QueryParam("size") Optional<Integer> size) {
        return articleRepository.findAll(size.orElse(defaultSize));
    }

    @GET
    @Path("/{id}")
    @Metered
    @ScopeAllowed(values = {Scope.IN, Scope.OUT})
    public Article getById(@PathParam("id") Long id) {
        return articleRepository
                .findById(id)
                .orElse(Article.EMPTY);
    }
}
