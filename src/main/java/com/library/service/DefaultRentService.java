package com.library.service;

import com.library.model.Customer;
import com.library.model.Rent;
import com.library.model.Volume;
import com.library.repository.interfaces.BookRepository;
import com.library.repository.interfaces.CustomerRepository;
import com.library.repository.interfaces.VolumeRepostiory;
import com.library.service.interfaces.RentService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class DefaultRentService implements RentService {
    private final BookRepository bookRepository;
    private final VolumeRepostiory volumeRepostiory;
    private final CustomerRepository customerRepository;

    public DefaultRentService(BookRepository bookRepository, VolumeRepostiory volumeRepostiory, CustomerRepository customerRepository) {
        this.bookRepository = bookRepository;
        this.volumeRepostiory = volumeRepostiory;
        this.customerRepository = customerRepository;
    }

    @Override
    public Rent rent(Integer volumeId, Customer customer) {
        Optional<Volume> optionalVolume = volumeRepostiory.findById(volumeId);
        if (!optionalVolume.isPresent()) {
            return null;
        } else {
            Volume volume = optionalVolume.get();
            volume.setRented(true);
            volumeRepostiory.save(volume);
            Optional<Customer> foundCustomer = customerRepository.findById(customer.getId());
            Rent rent = new Rent();
            rent.setVolume(volume);
            rent.setCustomer(foundCustomer.orElseGet(() -> customerRepository.save(customer)));
            rent.setRentDate(new Date());
            rent.setUntilDate(addDaysToDate(rent.getRentDate(), 30));
            return rent;
        }
    }

    public static Date addDaysToDate(Date date, int days){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        date = c.getTime();
        return date;
    }
}
