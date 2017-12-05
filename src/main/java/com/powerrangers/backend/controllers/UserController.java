package com.powerrangers.backend.controllers;

import com.powerrangers.backend.models.Customer;
import com.powerrangers.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Customer>> getAll() {
        Collection<Customer> collection = userService.getAll();
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @RequestMapping(
            value = "{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Customer> getUser(@PathVariable("id") Long id) {
        Customer customer = userService.getUser(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(
            value = "{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Customer> updateUser(@RequestBody Customer customer) {
        Customer updatedCustomer = userService.updateUser(customer);
        if (updatedCustomer == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @RequestMapping(
            value = "create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Customer> createUser(@RequestBody Customer customer) {
        Customer createdCustomer = userService.createUser(customer);
        if (createdCustomer != null) {
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(
            value = "{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Customer> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "reset",
            method = RequestMethod.POST
    )
    public ResponseEntity sendResetEmail(@RequestBody String username) {
        if (userService.resetEmailWasSent(username)) {
            return new ResponseEntity(HttpStatus.OK);
        } else return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @RequestMapping(
            value = "reset",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Customer> returnUserToReset(@RequestParam("token") String token) {
        Customer customer = userService.getByToken(token);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "reset",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Customer> resetPassword(@RequestBody Customer customer) {
        if (customer != null) {
            return new ResponseEntity<>(userService.resetPassword(customer), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}