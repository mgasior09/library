package com.library.repository.interfaces;

import com.library.model.Customer;

import java.util.List;

public interface CustomerFinder {
    List<Customer> getAll();
}
