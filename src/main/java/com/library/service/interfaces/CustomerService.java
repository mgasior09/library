package com.library.service.interfaces;

import com.library.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> showCustomers();
    Customer registerCustomer(Customer customer);
}
