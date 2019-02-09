package com.library.service.interfaces;

import com.library.model.Customer;
import com.library.model.User;
import com.library.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> showCustomers();
    Customer registerCustomer(Customer customer);
    User addCustomerToUserDatabase(Customer customer);
    UserRole addRoleToCustomer(User user);
    void deleteById(Integer id);
    void editCustomer(Integer id, Customer customer);
    Optional<Customer> getById(Integer workerId);

}
