package com.k_konsult.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;
import java.util.ArrayList;

import com.k_konsult.Dto.ArticleDto;
import com.k_konsult.Service.ArticleService;

@Controller
@CrossOrigin(origins = "https://k-konsult.bg")
@RequestMapping(path = "/K-Konsult/Article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /*@GetMapping(path="/Get")
    public ResponseEntity<ArticleDto> get(){
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle("null");
        articleDto.setContent("null");
        return ResponseEntity.ok(articleDto);
    }*/
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping (path = "/CreateArticle")
    public ResponseEntity<Map<String, String>> createArticle(@RequestBody ArticleDto articleDto)
    {
        return ResponseEntity.ok(articleService.createArticle(articleDto));
    }
    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping( path = "/Delete/title={title}")
    public ResponseEntity<Map<String, String>> deleteArticle(@PathVariable String title){
        return ResponseEntity.ok(articleService.deleteArtice(title));
    }
    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping( path = "/UpdateArticle")
    public ResponseEntity<Map<String, String>> updateArticle(@RequestBody ArticleDto articleDto){
        return ResponseEntity.ok(articleService.updateArticle(articleDto));
    }

    @GetMapping( path = "/Get/ArticleId/title={title}")
    public ResponseEntity<Map<String, String>> getArticleId(@PathVariable String title){
        return ResponseEntity.ok(articleService.getArticleId(title));
    }

    @GetMapping( path = "/Get/ArticleTitle/title={title}")
    public ResponseEntity<ArticleDto> getArticleTitle(@PathVariable String title){
        return ResponseEntity.ok(articleService.getArticleTitle(title));
    }

    @GetMapping( path = "/Get/AllArticle")
    public ResponseEntity<ArrayList<ArticleDto>> getAllArticle(){
        return ResponseEntity.ok(articleService.getAllArticle());
    }
}
