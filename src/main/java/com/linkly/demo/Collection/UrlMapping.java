package com.linkly.demo.Collection;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "mappings")
public class UrlMapping {

    @Id
    private long _id;

    @Length(min = 10)
    private String shortUrl;

    @Length(min = 10)
    private String longUrl;

    private String user_id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime terminated_at;

    /**
     * @return the _id
     */
    public long get_id() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void set_id(long _id) {
        this._id = _id;
    }

    /**
     * @return the shortUrl
     */
    public String getShortUrl() {
        return shortUrl;
    }

    /**
     * @param shortUrl the shortUrl to set
     */
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    /**
     * @return the longUrl
     */
    public String getLongUrl() {
        return longUrl;
    }

    /**
     * @param longUrl the longUrl to set
     */
    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the created_at
     */
    public LocalDateTime getCreated_at() {
        return created_at;
    }

    /**
     * @param created_at the created_at to set
     */
    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    /**
     * @return the terminated_at
     */
    public LocalDateTime getTerminated_at() {
        return terminated_at;
    }

    /**
     * @param terminated_at the terminated_at to set
     */
    public void setTerminated_at(LocalDateTime terminated_at) {
        this.terminated_at = terminated_at;
    }

}
