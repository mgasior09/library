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
        String pesel = customer.getPesel();

        String birthDate = "" + pesel.charAt(0) + pesel.charAt(1) +
                "-" + pesel.charAt(2) + pesel.charAt(3) +
                "-" + pesel.charAt(4) + pesel.charAt(5);

        int psize = pesel.length();
        if (psize != 11) {
            pesel = "";
        } else {
            int[] weights = {1, 3, 7, 9, 1, 3, 7 ,9 ,1 ,3};
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += Integer.parseInt(pesel.substring(i, i + 1)) * weights[i];
            }
            int control = Integer.parseInt(pesel.substring(10, 11));
            sum %= 10;
            sum = 10 - sum;
            sum %= 10;
            if (sum != control) {
                pesel = "";
            }
        }

        String sex;
        if (Character.getNumericValue(pesel.charAt(9)) % 2 == 0 ) {
            sex = "F";
        } else {
            sex = "M";
        }

        customer.setBirthDate(birthDate);
        customer.setSex(sex);
        return customerRepository.save(customer);
    }
}
