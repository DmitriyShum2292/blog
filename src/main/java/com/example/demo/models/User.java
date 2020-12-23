package com.example.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "my_user")
public class User {
    @Id
    private String login;
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Comment>comments;
    @OneToMany
    private List<Post>posts;
    private boolean active;

    private String password,nickname,authority,activationCode;

    public User() {
    }
    public User(String login,String nickname, String password,
                boolean active, String authority) {
        this.login = login;
        this.nickname=nickname;
        this.password = password;
        this.active = active;
        this.authority = authority;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAuthority() {
        return authority;
    }

    public String getNickname() {
        return nickname;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
