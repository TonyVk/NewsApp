package com.example.newsapp;

public class Sources {
    String id, name, description, url, category, language, country;

    public Sources(String id, String name, String description, String url, String category, String language, String country) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
    }

    public String getId() {return id;}
    public String getName() {return name;}
}
