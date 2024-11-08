package com.k_konsult.Service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.k_konsult.Dto.ArticleDto;
import com.k_konsult.Entity.Article;
import com.k_konsult.Repository.ArticleRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Map<String, String> createArticle(ArticleDto articleDto){
        Map<String, String> response = new HashMap<>();
        Optional<Article> article = articleRepository.findByTitle(articleDto.getTitle());
        if(article.isPresent()==false){
            Article new_article = new Article();
            new_article = articleDto.dtoToEntity(articleDto);
            articleRepository.saveAndFlush(new_article);
            response.put("message", "Новата статия е създадена успешно!");
            return response;
        }
        else{
            response.put("message", "Вече има такава статия!");
            return response;
        }
        
    }

    public Map<String, String> deleteArtice(String title){
        Map<String, String> response = new HashMap<>();
        Optional<Article> article = articleRepository.findByTitle(title);
        if(article.isPresent()==true){
            articleRepository.delete(article.get());
            response.put("message", "Статията е изтрита успешно!");
            return response;
        }
        else{
            response.put("message", "Няма такава статия!");
            return response;
        }
    }

    public Map<String, String> updateArticle(ArticleDto articleDto){
        Map<String, String> response = new HashMap<>();
        Optional<Article> article = articleRepository.findByTitle(articleDto.getTitle());
        if(article.isPresent()==true){
            article.get().setContent(articleDto.getContent());
            articleRepository.saveAndFlush(article.get());
            response.put("message", "Cтатия е променена успешно!");
            return response;
        }
        else{
            response.put("message", "Няма такава статия!");
            return response;
        }
        
    }

    public  Map<String, String>  getArticleId(String title){
        Map<String, String> response = new HashMap<>();
        Optional<Article> article = articleRepository.findByTitle(title);
        if(article.isPresent()==true){
            response.put("message", article.get().getArticleId()+"_folder");
            return response; 
        }
        else{
            response.put("message", "Няма такава статия!");
            return response; 
        }
        
    }

    public  ArticleDto  getArticleTitle(String title){
        Optional<Article> article = articleRepository.findByTitle(title);
        if(article.isPresent()==true){
            ArticleDto articleDto = new ArticleDto();
            articleDto = articleDto.entityToDto(article.get());
            return articleDto;
        }
        else{
            ArticleDto articleDto = new ArticleDto();
            articleDto = null;
            return articleDto; 
        }
        
    }


    public  ArrayList<ArticleDto>  getAllArticle(){
        List<Article> articles = articleRepository.findAll();
        ArrayList<ArticleDto>  arrayList = new ArrayList<ArticleDto>();
        for(int i=0; i<articles.size();i++){
            ArticleDto articleDto = new ArticleDto();
            articleDto = articleDto.entityToDto(articles.get(i));
            arrayList.add(articleDto);
        }
        return arrayList;
    }
}
