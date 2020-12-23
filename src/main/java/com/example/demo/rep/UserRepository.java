package com.example.demo.rep;

import com.example.demo.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User,String> {
    User findUserByLogin(String login);
    User findByActivationCode(String code);
    User findUserByNickname(String nickname);
    ArrayList<User> findAll();
}
