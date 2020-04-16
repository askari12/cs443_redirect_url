package com.linkly.demo.secondary;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "urlClicks")
public class UrlClicksEntity {

    private String shortURL;
    private String timeStamp;

    public UrlClicksEntity(String shortURL , String timeStamp) {
        this.shortURL = shortURL;
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getShortURL() {
        return  shortURL;
    }


    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setShortURL(String url) {
        this.shortURL = url;
    }
}
