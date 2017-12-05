package com.powerrangers.backend.services;

import com.powerrangers.backend.models.Customer;
import com.powerrangers.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MailService mailService;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, MailService mailService, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.encoder = encoder;
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
        customer.setPassword(encoder.encode(customer.getPassword()));
        return userRepository.save(customer);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public boolean resetEmailWasSent(String username) {
        if (!usernameExists(username)) {
            return false;
        }
        String token = UUID.randomUUID().toString();
        userRepository.findByUsername(username).setResetToken(token);
        SimpleMailMessage simpleMailMessage = mailService.createResetEmail(username, token);
        mailService.sendEmail(simpleMailMessage);
        return true;
    }

    public Customer getByToken(String token) {
        return userRepository.findByResetToken(token);
    }

    public Customer resetPassword(Customer customer) {
        Customer customerToUpdate = userRepository.findByUsername(customer.getUsername());
        customerToUpdate.setPassword(encoder.encode(customer.getPassword()));
        customerToUpdate.setResetToken(null);
        return userRepository.save(customerToUpdate);
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

    private boolean usernameExists(String username) {
        String existingUsername;
        List<Customer> list = userRepository.findAll();
        for (Customer aList : list) {
            existingUsername = aList.getUsername();
            if (existingUsername.equals(username)) {
                return true;
            }
        }
        return false;
    }

}
