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
    public String getDescription() {return description;}
    public String getUrlToImage() {return urlToImage;}
    public String getUrl() {return url;}
}
