package com.library.controller;

import com.library.model.Customer;
import com.library.service.interfaces.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String printAllCustomers(Model model) {
        List<Customer> customers = customerService.showCustomers();
        customers.sort(Comparator.comparing(Customer::getId));
        model.addAttribute("customersList", customers);
        return "customers";
    }

    @GetMapping("/register")
    public String initCustomerRegistrationForm(Model model) {
        model.addAttribute("registeredCustomer", new Customer());
        return "registerCustomer";
    }

    @PostMapping
    public String registerCustomer(
            @Valid @ModelAttribute("registeredCustomer") Customer customer,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registerCustomer";
        }
        customerService.registerCustomer(customer);
        return "redirect:/customers";
    }
}
