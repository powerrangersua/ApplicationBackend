package com.powerrangers.backend.controllers;

import com.powerrangers.backend.service.UserService;
import com.powerrangers.backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/all")
    public Collection<User> getAll(){
        return userService.getAll();
    }

    @RequestMapping("{id}")
    public User getUser(@PathVariable("id") Long id){
        return userService.getUser(id);
    }
}
