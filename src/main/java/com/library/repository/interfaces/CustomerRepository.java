package com.library.repository.interfaces;

import com.library.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< Updated upstream
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

=======
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findById (Integer customerId);
    Optional<Customer> findByPesel (String pesel);
    List<Customer> findAll();
>>>>>>> Stashed changes
}
