package com.linkly.demo.primary;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection =  "urlEntity")
public class UrlEntity {

    @Id
    private String id;
    private String longURL;

    public UrlEntity(String id , String longURL) {
        this.id = id;
        this.longURL = longURL;
    }

    // Getters and Setters
    public String getShortURL() {
        return id;
    }

    public String getLongURL() {
        return longURL;
    }

    public void setShortURL(String newURL) {
        this.id = newURL;
    }

    public void setLongURL(String newURL) {
        this.longURL = newURL;
    }
}
