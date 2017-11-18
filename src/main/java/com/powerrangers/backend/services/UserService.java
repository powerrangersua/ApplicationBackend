package com.powerrangers.backend.service;

import com.powerrangers.backend.repository.UserRepository;
import com.powerrangers.backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id){
        return userRepository.findOne(id);
    }

    public Collection<User> getAll(){
        return userRepository.findAll();
    }
}
