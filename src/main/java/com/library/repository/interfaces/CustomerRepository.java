package com.library.repository.interfaces;

import com.library.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAll();

    Optional<Customer> findById(Integer customerId);

    Optional<Customer> findByPesel(String pesel);
}
