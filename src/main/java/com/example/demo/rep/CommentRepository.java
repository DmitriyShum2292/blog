package com.example.demo.rep;

import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    ArrayList<Comment> findAllByPost(Post currentPost);
    ArrayList<Comment> findAllByUser(User user);
    Comment findCommentById(long id);
}
