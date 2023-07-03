package com.springrest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springrest.model.RedditPostStructure;
import com.springrest.service.HotPostRedditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RedditServiceController {

    private final HotPostRedditService redditService;

    @Autowired
    public RedditServiceController(HotPostRedditService redditService){
        this.redditService = redditService;
    }

    // localhost:8099/subredditname to store and see all hot posts on that subreddit ,and also it add posts to database if it doesn't exist
    @GetMapping("/{subredditname}")
    public List<RedditPostStructure> getAllComments(@PathVariable String subredditname) throws JsonProcessingException {
        return redditService.readArticles(subredditname);
    }

    // localhost:8099/search/keyword to see all hot posts containing that word
    @GetMapping("/search/{word}")
    public List<RedditPostStructure> searchByWord(@PathVariable String word){
        return redditService.findByWord(word);
    }

    // localhost:8099/delete/subredditname to see and delete all posts on that subreddit
    @GetMapping("/delete/{subredditname}")
    public List<RedditPostStructure> deleteByUser(@PathVariable String subredditname){
        return redditService.deleteBySubreddit(subredditname);
    }

    // localhost:8099/sort to see all posts in sorted order according to created time stamp on reddit
    @GetMapping("/sort")
    public List<RedditPostStructure> sortByCreated(){
        return redditService.sortData();
    }

    // localhost:8099/reddit/addpost and provide description of post to add it in desired subreddit
    @PostMapping("reddit/addpost")
    public String createNewPost(String subreddit,String title,String text) {
        String authToken = redditService.getAuthToken();
        return redditService.postArticle(subreddit,title,text,authToken);
    }

    // It fetches in all existing subreddits in 2 minutes and add it into our database if it doesn't exist before.
    @Scheduled(fixedDelay = 120000, initialDelay = 0)
    public void sync() throws JsonProcessingException {
        System.out.println("Started syncing posts");
        List<String> subreddits = redditService.fetchAllSubreddits();
        for (String subreddit: subreddits) {
            redditService.readArticles(subreddit);
        }
        System.out.println("Stopped syncing posts");
    }
}
