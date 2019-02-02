package com.library.controller;

import com.library.model.Customer;
import com.library.service.interfaces.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/add")
    public String initCustomerRegistrationForm(Model model, Customer customer) {
        model.addAttribute("registeredCustomer", customer);
        return "addCustomer";
    }

    @PostMapping
    public String registerCustomer(
            @Valid @ModelAttribute("registeredCustomer") Customer customer,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addCustomer";
        }

        try {
            customerService.registerCustomer(customer);
            customerService.addRoleToCustomer(customerService.addCustomerToUserDatabase(customer));
        } catch (StringIndexOutOfBoundsException e) {
            return "redirect:/customers/add";
        }

        return "redirect:/customers";
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Integer customerID, Model model) {
        customerService.deleteById(customerID);
        return "redirect:/customers";
    }

}
