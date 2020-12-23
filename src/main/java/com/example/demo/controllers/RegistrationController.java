package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.MailSender;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;
import java.util.UUID;

@Controller
public class RegistrationController {

    @Autowired
    private MailSender mailSender;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addNewUser(@RequestParam String login,@RequestParam String nickname,
                             @RequestParam String password,@RequestParam String repassword, Model model){
        if (StringUtils.isEmpty(login)||StringUtils.isEmpty(password)
            ||StringUtils.isEmpty(repassword)){
            model.addAttribute("message","Заполните все поля!");
            return "registration";
        }
        if (!password.equals(repassword)){
            model.addAttribute("message","Пароли не совпадают!");
            return "registration";
        }
        User user = new User(login,nickname,password,false,"ROLE_USER");
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userService.userExist(user.getLogin())) {
            model.addAttribute("message", "Такой пользователь уже существует.");
            return "registration";
        }
        userService.saveUser(user);
        mailSender.send(user.getLogin(),"Activation code",user);
        model.addAttribute("message","Вам на почту было выслано письмо,с ссылкой для активации аккаунта.Перейдите по ней.");
        return "login";
    }
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "Вы успешно активировали свой аккаунт");
        }
        else {
            model.addAttribute("message", "Код активации не действителен");
        }
        return "login";
    }
}
