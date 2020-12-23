package com.example.demo.controllers;

import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.services.CommentService;
import com.example.demo.services.PostService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;

    @GetMapping("/user")
    public String userPage(Model model){
        User user = userService.findCurrentUser();
        ArrayList <Comment> comments = commentService.findAllCommentsByUser(user);
        List<Post> mostViewed = postService.getMostViewedPosts(0,5);
        model.addAttribute("email",user.getLogin());
        model.addAttribute("nickname",user.getNickname());
        model.addAttribute("res",comments);
        model.addAttribute("mostViewed",mostViewed);
        return "user";
    }
}
