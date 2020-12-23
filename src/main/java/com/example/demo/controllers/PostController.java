package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.services.FileService;
import com.example.demo.services.PostService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


@Controller
public class PostController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;

    @GetMapping("/post/add")
    public String add(Model model){
        Post post = new Post();
        model.addAttribute(post);
        return "add";
    }
    @PostMapping("/post/add")
    public String addPost(@RequestParam Map<String,String> all, @RequestParam MultipartFile titleImage,
                          @RequestParam MultipartFile p1Image, @RequestParam MultipartFile p2Image) throws IOException {
        Post post = new Post(all.get("title"),all.get("anons"),all.get("p1Text"),all.get("p2Text"),all.get("p3Text"),
                all.get("source"),all.get("video"));
        post.setUser(userService.findCurrentUser());
        Post postWithFiles = fileService.uploadPostFiles(titleImage,p1Image,p2Image,post);
        if (post.getVideo().isEmpty()) {
            postWithFiles.setVideo(null);
        }
        postService.savePost(postWithFiles);
        return "redirect:/";
    }
    @GetMapping("/post/{id}/edit")
    public String edit(@PathVariable(value = "id")long id, Model model){
        ArrayList<Post>res = postService.findArrayPostById(id);
        model.addAttribute("res",res);
        return "edit";
    }
    @PostMapping("/post/update")
    public String editUpdate(@RequestParam Map<String,String> all, @RequestParam MultipartFile titleImage,
                             @RequestParam MultipartFile p1Image, @RequestParam MultipartFile p2Image) throws IOException {
        long postId = Integer.parseInt(all.get("id"));
        Post post = postService.findPostById(postId);
        post.setTitle(all.get("title"));
        post.setAnons(all.get("anons"));
        post.setP1Text(all.get("p1Text"));
        post.setP2Text(all.get("p2Text"));
        post.setP3Text(all.get("p3Text"));
        post.setSource(all.get("source"));
        post.setVideo(all.get("video"));
        post.setUser(userService.findCurrentUser());
        Post updatedPost = fileService.uploadPostFiles(titleImage,p1Image,p2Image,post);
        postService.savePost(updatedPost);
        return "redirect:/post/"+postId;
    }
    @PostMapping("/post/{id}/remove")
    public String delete(@PathVariable(value = "id")long id) throws IOException {
        Post post = postService.findPostById(id);
        fileService.deletePostFiles(post);
        postService.deletePost(post);
        return "redirect:/";
    }
}
