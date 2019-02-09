package com.library.service;

import com.library.model.Customer;
import com.library.model.Rent;
import com.library.model.Volume;
import com.library.repository.interfaces.CustomerRepository;
import com.library.repository.interfaces.RentRepository;
import com.library.repository.interfaces.VolumeRepository;
import com.library.repository.interfaces.WorkerRepository;
import com.library.service.interfaces.RentService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultRentService implements RentService {
    private final VolumeRepository volumeRepository;
    private final CustomerRepository customerRepository;
    private final RentRepository rentRepository;
    private final WorkerRepository workerRepository;

    public DefaultRentService(VolumeRepository volumeRepository, CustomerRepository customerRepository, RentRepository rentRepository, WorkerRepository workerRepository) {
        this.volumeRepository = volumeRepository;
        this.customerRepository = customerRepository;
        this.rentRepository = rentRepository;
        this.workerRepository = workerRepository;
    }

    @Override
    public Rent rent(Integer volumeId, Customer customer, Rent rent, Principal principal) {
        Optional<Volume> optionalVolume = volumeRepository.findById(volumeId);
        if (!optionalVolume.isPresent()) {
            return null;
        } else {
            Volume volume = optionalVolume.get();
            if (volume.isReserved()) {
                volume.setRented(true);
                volumeRepository.save(volume);
                Optional<Customer> foundCustomer = customerRepository.findById(customer.getId());
                rent.setVolume(volume);
                rent.setCustomer(foundCustomer.orElseGet(() -> customerRepository.save(customer)));
                rent.setRentDate(new Date());

                String name = principal.getName();
                Integer id = workerRepository.findByLogin(name).get().getId();

                rent.setWorker(workerRepository.findById(id).get());
                rent.setUntilDate(addDaysToDate(rent.getRentDate(), 30));
                return rentRepository.save(rent);
            }
        } return null;
    }

    @Override
    public List<Rent> getAllCustomerRents(String pesel) {
        return rentRepository.findByCustomerPeselEquals(pesel);
    }


    public static Date addDaysToDate(Date date, int days){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        date = c.getTime();
        return date;
    }
}
