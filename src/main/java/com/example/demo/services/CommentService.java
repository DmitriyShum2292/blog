package com.example.demo.services;

import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.rep.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public ArrayList<Comment> findAllCommentsByPost(Post post){
        ArrayList<Comment> comments = commentRepository.findAllByPost(post);
        return comments;
    }
    public ArrayList<Comment> findAllCommentsByUser(User user){
        ArrayList<Comment> comments = commentRepository.findAllByUser(user);
        return comments;
    }
    public boolean saveComment(Comment comment){
        Date date = new Date();
        comment.setDate(date);
        commentRepository.save(comment);
        return true;
    }
    public Comment findCommentById(long id){
        Comment comment = commentRepository.findCommentById(id);
        return comment;
    }
    public boolean deleteComment(Comment comment){
        commentRepository.delete(comment);
        return true;
    }
}
