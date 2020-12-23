package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.rep.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserById(String id){
        User user = userRepository.findUserByLogin(id);
        return user;
    }
    public User findUserByNick(String nickname){
        User user = userRepository.findUserByNickname(nickname);
        return user;
    }
    public User findUserByLogin(String login){
        User user = userRepository.findUserByLogin(login);
        return user;
    }
    public boolean userExist(String login){
        Optional<User> findUser = userRepository.findById(login);
        if (findUser.isEmpty()){
            return false;
        }
        return true;
    }
    public User findCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> user = userRepository.findById(username);
        User currentUser = user.get();
        return currentUser;
    }
    public ArrayList<User> findAllUsers(){
        ArrayList<User> users = userRepository.findAll();
        return  users;
    }
    public boolean saveUser(User user){
            userRepository.save(user);
            return true;
    }
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if(user == null){
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);

        userRepository.save(user);
        return  true;
    }
    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
