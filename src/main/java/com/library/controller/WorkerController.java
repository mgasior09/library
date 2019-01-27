package com.library.controller;

import com.library.model.Worker;
import com.library.service.interfaces.WorkerService;
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
@RequestMapping("/workers")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    public String printAllWorkers(Model model) {
        List<Worker> workers = workerService.showWorkers();
        workers.sort(Comparator.comparing(Worker::getId));
        model.addAttribute("workersList", workers);
        return "workers";
    }

    @GetMapping("/add")
    public String initWorkerRegistrationForm(Model model, Worker worker) {
        model.addAttribute("registeredWorker", worker);
        return "addWorker";
    }

    @PostMapping
    public String registerWorker(
            @Valid @ModelAttribute("registeredWorker") Worker worker,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addWorker";
        }
        workerService.registerWorker(worker);
        workerService.addRoleToUser(workerService.addWorkerToUserDatabase(worker));
        workerService.addWorkerToUserDatabase(worker);
        return "redirect:/workers";
    }
}
