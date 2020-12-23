package com.example.demo.controllers;

import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.services.CommentService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String adminPage(Model model){
        ArrayList<User> users = userService.findAllUsers();
        model.addAttribute("users",users);
        return "admin";
    }
    @PostMapping
    public String findUserByNick(@RequestParam String nickname, Model model){
        ArrayList<User> users = userService.findAllUsers();
        model.addAttribute("users",users);
        User user = userService.findUserByNick(nickname);
        model.addAttribute("user",user);
        return "admin";
    }
    @PostMapping("/block/{id}")
    public String blockUser(@PathVariable(value = "id")String id){
        User user = userService.findUserById(id);
        user.setActive(false);
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @PostMapping("/unblock/{id}")
    public String unblockUser(@PathVariable(value = "id")String id){
        User user = userService.findUserById(id);
        user.setActive(true);
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @PostMapping("/delete/comment/{id}")
    public String deleteComment(@PathVariable(value = "id")long id){
        Comment comment = commentService.findCommentById(id);
        Post post = comment.getPost();
        commentService.deleteComment(comment);
        return "redirect:/post/"+post.getId();
    }
}
