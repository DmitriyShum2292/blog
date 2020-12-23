package com.example.demo.services;

import com.example.demo.models.Post;
import com.example.demo.rep.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public ArrayList<Post> findAllPosts(){
        ArrayList<Post> posts = postRepository.findAll();
        return posts;
    }
    public ArrayList<Post> findArrayPostById(long id){
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        return res;
    }
    public boolean savePost(Post post){
        Date date = new Date();
        post.setDate(date);
        String dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date);
        post.setDateFormat(dateFormat);
        postRepository.save(post);
        return true;
    }
    public Post findPostById(long postId){
        Post post = postRepository.findById(postId).orElseThrow();
        return post;
    }
    public boolean deletePost(Post post){
        postRepository.delete(post);
        return true;
    }
    public List<Post> getPagePosts(int offset,int limit){
        ArrayList<Post> posts = (ArrayList<Post>) postRepository.getAllPostSortByDate();
        List<Post> result =
                posts.stream()
                        .skip(offset)
                        .limit(limit)
                        .collect(Collectors.toList());
        return result;
    }
    public void addViews(Post post){
        post.setViews(post.getViews()+1);
        postRepository.save(post);
    }
    public List<Post> getMostViewedPosts(int offset,int limit){
        ArrayList<Post> allPostsByViews = postRepository.getAllPostSortByViews();
        List<Post> result =
                allPostsByViews.stream()
                        .skip(offset)
                        .limit(limit)
                        .collect(Collectors.toList());
        return result;
    }
}
