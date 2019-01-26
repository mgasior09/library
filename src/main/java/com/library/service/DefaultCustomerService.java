package com.library.service;

        import com.library.model.Customer;
        import com.library.repository.interfaces.CustomerRepository;
        import com.library.service.interfaces.CustomerService;
        import org.springframework.stereotype.Service;

        import java.util.Date;
        import java.util.List;

@Service
public class DefaultCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;

    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> showCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        customer.setAdded(new Date());
        return customerRepository.save(customer);
    }
}
