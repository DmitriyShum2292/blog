package com.example.demo.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "id",unique = true,nullable = false)
    private long id;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
    @Column(length = 1000)
    private String text;
    private Date date;

    public Comment() {
    }
    public Comment(Post post, User user, String text) {
        this.post = post;
        this.user = user;
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }
}
