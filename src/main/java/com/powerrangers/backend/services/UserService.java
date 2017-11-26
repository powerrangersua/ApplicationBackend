package com.powerrangers.backend.services;

import com.powerrangers.backend.models.Role;
import com.powerrangers.backend.models.User;
import com.powerrangers.backend.repositories.RoleRepository;
import com.powerrangers.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void save(User user) {
        List<Role> allRoles = roleRepository.findAll();
        Role[] rolesArray = new Role[allRoles.size()];
        user.setRoles(allRoles.toArray(rolesArray));
        userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        User userToUpdate = getUser(user.getUserId());
        if (userToUpdate == null) {
            throw new NoResultException("User not found");
        }
        userToUpdate.setName(user.getName());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setAge(user.getAge());

        return userRepository.save(userToUpdate);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
