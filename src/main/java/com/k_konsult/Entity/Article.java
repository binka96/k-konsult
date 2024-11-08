package com.k_konsult.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int articleId;
    private String title;
    private String imageFolder;
    @Lob
    private String content;

    public void setArticleId(int _articleId){
        this.articleId = _articleId;
    }

    public int getArticleId(){
        return this.articleId;
    }

    public void setTitle(String _title){
        this.title = _title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setImageFolder(String _imageFolder){
        this.imageFolder = _imageFolder;
    }

    public String getImageFolder(){
        return this.imageFolder;
    }

    public void setContent(String _content){
        this.content = _content;
    }

    public String getContent(){
        return this.content;
    }
}
