package com.springrest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "postCollection")
public class RedditPostStructure {
    @Id
    private String id;
    private String subreddit;
    private String title;
    private Double created;
    public RedditPostStructure(){}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSubreddit() {
        return subreddit;
    }
    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Double getCreated() {
        return created;
    }
    public void setCreated(Double created) {
        this.created = created;
    }

    public RedditPostStructure(String subreddit, String title, Double created) {
        this.subreddit = subreddit;
        this.title = title;
        this.created = created;
    }
}