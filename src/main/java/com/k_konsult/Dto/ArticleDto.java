package com.k_konsult.Dto;

import org.modelmapper.ModelMapper;
import com.k_konsult.Entity.Article;

public class ArticleDto {
    private  ModelMapper modelMapper = new ModelMapper();
    private String title;
    private String content;

    public void setTitle(String _title){
        this.title = _title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setContent(String _content){
        this.content = _content;
    }

    public String getContent(){
        return this.content;
    }

    public Article dtoToEntity(ArticleDto articleDto) {
        return modelMapper.map(articleDto, Article.class);
    }

    public  ArticleDto entityToDto(Article article) {
    return modelMapper.map(article, ArticleDto.class);
    }
}
