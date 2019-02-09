package com.library.service.interfaces;

import com.library.model.Customer;
import com.library.model.Rent;

import java.security.Principal;
import java.util.List;

public interface RentService {
    Rent rent(Integer bookId, Customer customer, Rent rent, Principal principal);

    List<Rent> getAllCustomerRents (String pesel);

}
