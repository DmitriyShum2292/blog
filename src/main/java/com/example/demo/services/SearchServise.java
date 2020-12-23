package com.example.demo.services;

import com.example.demo.models.Post;
import com.example.demo.rep.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchServise {
    @Autowired
    private PostRepository postRepository;

    public ArrayList<Post> findPosts(String key){
        ArrayList<Post> posts = postRepository.findAll();
        ArrayList<Post> result  = new ArrayList<>();
        for (Post post:posts) {
            int index  = post.getTitle().indexOf(key);
            if (index==-1){

            }
            else {
                result.add(post);
            }
        }
        return result;
    }
}
