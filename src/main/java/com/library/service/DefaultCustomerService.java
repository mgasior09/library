package com.library.service;

import com.library.model.Customer;
import com.library.repository.interfaces.CustomerFinder;
import com.library.repository.interfaces.CustomerRepository;
import com.library.service.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerFinder customerFinder;

    public DefaultCustomerService(CustomerRepository customerRepository, CustomerFinder customerFinder) {
        this.customerRepository = customerRepository;
        this.customerFinder = customerFinder;
    }

    @Override
    public List<Customer> showCustomers() {
        return customerFinder.getAll();
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
