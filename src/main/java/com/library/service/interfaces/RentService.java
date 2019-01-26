package com.library.service.interfaces;

import com.library.model.Customer;
import com.library.model.Rent;

import java.util.List;

public interface RentService {
    Rent rent(Integer bookId, Customer customer);

    List<Rent> getAllCustomerRents (String pesel);

}
