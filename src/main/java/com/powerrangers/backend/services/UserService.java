package com.powerrangers.backend.services;

import com.powerrangers.backend.repositories.UserRepository;
import com.powerrangers.backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
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

    public User updateUser(User user){
        User userToUpdate = getUser(user.getUserId());
        if (userToUpdate == null) {
            throw new NoResultException("User not found");
        }
        userToUpdate.setName(user.getName());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setAge(user.getAge());

        return userRepository.save(userToUpdate);
    }
}
