package com.powerrangers.backend.repositories;

import com.powerrangers.backend.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}
