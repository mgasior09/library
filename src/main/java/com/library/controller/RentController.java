package com.library.controller;

import com.library.model.Rent;
import com.library.repository.interfaces.CustomerRepository;
import com.library.service.interfaces.CustomerService;
import com.library.service.interfaces.RentService;
import com.library.service.interfaces.VolumeService;
import com.library.service.interfaces.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rent")
public class RentController {
    private final WorkerService workerService;
    private final VolumeService volumeService;
    private final CustomerService customerService;
    private final RentService rentService;
    private final CustomerRepository repository;

    public RentController(WorkerService workerService, VolumeService volumeService, CustomerService customerService, RentService rentService, CustomerRepository repository) {
        this.workerService = workerService;
        this.volumeService = volumeService;
        this.customerService = customerService;
        this.rentService = rentService;
        this.repository = repository;
    }


    @GetMapping("/{id}")
    public String confirmRent(@PathVariable("id") Integer volumeId, String pesel, Rent rent) {
        rentService.rent(volumeId, customerService.getById(18).get(), rent);
        return "redirect:/books";
    }
}
