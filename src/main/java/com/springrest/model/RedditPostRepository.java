package com.springrest.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedditPostRepository extends MongoRepository<RedditPostStructure, String> {
    List<RedditPostStructure> findAllBySubreddit(String subreddit);
}