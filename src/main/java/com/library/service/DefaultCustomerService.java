package com.library.service;

import com.library.model.Customer;
import com.library.repository.interfaces.CustomerRepository;
import com.library.service.interfaces.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class DefaultCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;

    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
