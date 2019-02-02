package com.library.controller;

import com.library.model.Worker;
import com.library.service.interfaces.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
        workerService.addRoleToWorker(workerService.addWorkerToUserDatabase(worker));
        return "redirect:/workers";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer workerId) {
        workerService.deleteById(workerId);
        return "redirect:/workers";
    }

    @GetMapping("/edit/{id}")
    public String initWorkerEditForm(@PathVariable("id") Integer workerId, Model model) {
        Optional<Worker> foundWorker = workerService.getById(workerId);
        foundWorker.ifPresent(worker -> model.addAttribute("editWorker", worker));
        return "editWorker";
    }

    @PostMapping("/edit")
    public String editWorker(@ModelAttribute Worker worker) {
        workerService.editPassword(worker.getId());
        return "redirect:/workers";
    }
}
