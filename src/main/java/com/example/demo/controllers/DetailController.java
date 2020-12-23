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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/post")
public class DetailController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public String details(@PathVariable(value = "id") long id, Model model){
        ArrayList<Post> res = postService.findArrayPostById(id);
        model.addAttribute("res",res);
        Post currentPost = postService.findPostById(id);
        ArrayList<Comment> comments = commentService.findAllCommentsByPost(currentPost);
        List<Post> mostViewed = postService.getMostViewedPosts(0,5);
        model.addAttribute("comments",comments);
        model.addAttribute("mostViewed",mostViewed);
        model.addAttribute("title",currentPost.getTitle());
        postService.addViews(currentPost);
        return "details";
    }
    @PostMapping("/addcomment")
    public String addComment(@RequestParam String id,@RequestParam String commenttext){
        long postId = Integer.parseInt(id);
        Post currentPost = postService.findPostById(postId);
        User currentUser = userService.findCurrentUser();
        Comment comment = new Comment(currentPost,currentUser,commenttext);
        commentService.saveComment(comment);
        return "redirect:/post/"+postId;
    }

}
