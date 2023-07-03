package com.springrest.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RedditPostRepository extends MongoRepository<RedditPostStructure, String> {
    List<RedditPostStructure> findAllBySubreddit(String subreddit);
}