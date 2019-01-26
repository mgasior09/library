package com.library.repository.interfaces;

import com.library.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Integer> {
    List<Rent> findByCustomerPeselEquals(String pesel);

}
