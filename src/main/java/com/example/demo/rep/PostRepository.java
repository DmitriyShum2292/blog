package com.example.demo.rep;

import com.example.demo.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface PostRepository extends CrudRepository<Post,Long> {
    ArrayList<Post>findAll();
    @Query("SELECT u FROM Post u ORDER BY u.views DESC")
    ArrayList<Post> getAllPostSortByViews();
    @Query("SELECT u FROM Post u ORDER BY u.date DESC")
    Collection<Post> getAllPostSortByDate();
}
