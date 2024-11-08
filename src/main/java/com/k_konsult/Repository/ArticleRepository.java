package com.k_konsult.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.k_konsult.Entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Optional <Article> findByTitle(String title);
}
