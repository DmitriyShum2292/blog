package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.services.PostService;
import com.example.demo.services.SearchServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PostService postService;
    @Autowired
    private SearchServise searchService;

    @GetMapping("/")
    public String mainPage(Model model){
        List<Post> posts = postService.getPagePosts(0,5);
        List<Post> mostViewed = postService.getMostViewedPosts(0,5);
        model.addAttribute("posts",posts);
        model.addAttribute("mostViewed",mostViewed);
        return "main";
    }
    @GetMapping("/page/{id}")
    public String pages(@PathVariable(value = "id")int id,Model model){
        int offset,limit;
        limit = 5*id;
        offset = limit-5;
        List<Post> posts = postService.getPagePosts(offset,limit);
        List<Post> allPosts = postService.findAllPosts();
        double last = Math.ceil(allPosts.size()/5);
        long lastPage = (long) last;
        List<Post> mostViewed = postService.getMostViewedPosts(0,5);
        model.addAttribute("lastPage",lastPage);
        model.addAttribute("posts",posts);
        model.addAttribute("id",id);
        model.addAttribute("mostViewed",mostViewed);
        return "main";
    }
    @PostMapping("/search")
    public String search(@RequestParam String key,Model model){
        ArrayList<Post> result = searchService.findPosts(key);
        List<Post> mostViewed = postService.getMostViewedPosts(0,5);
        model.addAttribute("result",result);
        model.addAttribute("mostViewed",mostViewed);
        return "search";
    }
}
