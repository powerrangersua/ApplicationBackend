package com.powerrangers.backend.services;

import com.powerrangers.backend.models.Customer;
import com.powerrangers.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Customer getUser(Long id) {
        return userRepository.findOne(id);
    }

    public Collection<Customer> getAll() {
        return userRepository.findAll();
    }

    public Customer updateUser(Customer customer) {
        Customer customerToUpdate = getUser(customer.getUserId());
        if (customerToUpdate == null) {
            throw new NoResultException("Customer not found");
        }
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setSurname(customer.getSurname());
        customerToUpdate.setAge(customer.getAge());

        return userRepository.save(customerToUpdate);
    }

    public Customer createUser(Customer customer) {
        if (usernameExists(customer)) {
            return null;
        }
        return userRepository.save(customer);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    private boolean usernameExists(Customer newCustomer) {
        String existingUsername;
        String usernameToCheck = newCustomer.getUsername();
        List<Customer> list = userRepository.findAll();
        for (Customer aList : list) {
            existingUsername = aList.getUsername();
            if (existingUsername.equals(usernameToCheck)) {
                return true;
            }
        }
        return false;
    }
}
