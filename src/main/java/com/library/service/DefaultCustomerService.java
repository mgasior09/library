package com.library.service;

        import com.library.model.Customer;
        import com.library.model.User;
        import com.library.model.UserRole;
        import com.library.model.Worker;
        import com.library.repository.interfaces.CustomerRepository;
        import com.library.repository.interfaces.UserRepository;
        import com.library.repository.interfaces.UserRoleRepository;
        import com.library.service.interfaces.CustomerService;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.Date;
        import java.util.List;
        import java.util.Optional;

@Service
public class DefaultCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    public DefaultCustomerService(CustomerRepository customerRepository, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
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

    @Override
    public User addCustomerToUserDatabase(Customer customer) {
        User user = new User();
        user.setUsername(customer.getPesel());
        user.setPassword(customer.getPassword());
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public UserRole addRoleToCustomer(User user) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRoleName("ROLE_USER");
        return userRoleRepository.save(userRole);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if (foundCustomer.isPresent()) {
            String pesel = foundCustomer.get().getPesel();
            userRepository.deleteByUsername(pesel);
        }

        customerRepository.deleteById(id);
    }

    @Override
    public void editCustomer(Integer id, Customer customer) {
        Customer oldCustomer = customerRepository.findById(id).get();
        oldCustomer.setCity(customer.getCity());
        oldCustomer.setLastName(customer.getLastName());
        oldCustomer.setName(customer.getName());
        oldCustomer.setStreet(customer.getStreet());
        oldCustomer.setZipCode(customer.getZipCode());

        String password = customer.getPassword();
        String pesel = oldCustomer.getPesel();
        User user = userRepository.findByUsername(pesel).get();
        user.setPassword(password);
        oldCustomer.setPassword(password);
        oldCustomer.setModified(new Date());
        userRepository.save(user);
        customerRepository.save(oldCustomer);
    }

    @Override
    public Optional<Customer> getById(Integer workerId) {
        return customerRepository.findById(workerId);
    }



}
