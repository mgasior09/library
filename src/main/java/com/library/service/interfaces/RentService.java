package com.library.service.interfaces;

import com.library.model.Customer;
import com.library.model.Rent;

public interface RentService {
    Rent rent(Integer bookId, Customer customer);

}
