package com.example.newsapp;

public class News {
    String title, author, description, date, urlToImage, url;

    public News(String title, String author, String description, String date, String urlToImage, String url) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.date = date;
        this.urlToImage = urlToImage;
        this.url = url; }

    public String getTitle() {return title; }
    public void setTitle(String title) {this.title = title; }
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
    public String getDescription() {return description;}
    public void setDescription(String desc) {this.description = desc;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
    public String getUrlToImage() {return urlToImage;}
    public void setUrlToImage(String urlToImage) {this.urlToImage = urlToImage;}
    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}
}
