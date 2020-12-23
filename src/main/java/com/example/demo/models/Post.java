package com.example.demo.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Comment>comments;
    @ManyToOne
    @JoinColumn(name = "author")
    private User user;
    private Date date;
    private String dateFormat;
    private long views;
    private String title,titleImage,p1Image,p2Image,video,source;
    @Column(length = 1000)
    private String anons;
    @Column(length = 2000)
    private String p1Text;
    @Column(length = 2000)
    private String p2Text;
    @Column(length = 2000)
    private String p3Text;

    public Post() {
    }
    public Post(String title, String anons, String p1Text,String p2Text,
                String p3Text,String source,String video) {
        this.title = title;
        this.anons = anons;
        this.p1Text = p1Text;
        this.p2Text = p2Text;
        this.p3Text = p3Text;
        this.source = source;
        this.video = video;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String filename) {
        this.titleImage = filename;
    }

    public String getP1Image() {
        return p1Image;
    }

    public void setP1Image(String p1Image) {
        this.p1Image = p1Image;
    }

    public String getP2Image() {
        return p2Image;
    }

    public void setP2Image(String p2Image) {
        this.p2Image = p2Image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getP1Text() {
        return p1Text;
    }

    public void setP1Text(String p1Text) {
        this.p1Text = p1Text;
    }

    public String getP2Text() {
        return p2Text;
    }

    public void setP2Text(String p2Text) {
        this.p2Text = p2Text;
    }

    public String getP3Text() {
        return p3Text;
    }

    public void setP3Text(String p3Text) {
        this.p3Text = p3Text;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
