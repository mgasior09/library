package com.library.service;

import com.library.model.User;
import com.library.model.Worker;
import com.library.repository.interfaces.UserRepository;
import com.library.repository.interfaces.WorkerRepository;
import com.library.service.interfaces.WorkerService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DefaultWorkerService implements WorkerService {
    private final WorkerRepository workerRepository;
    private final UserRepository userRepository;

    public DefaultWorkerService(WorkerRepository workerRepository, UserRepository userRepository) {
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Worker> showWorkers() {
        return workerRepository.findAll();
    }

    @Override
    public Worker registerWorker(Worker worker) {
        worker.setAdded(new Date());
        return workerRepository.save(worker);
    }

    public User addWorkerToUserDatabase(Worker worker) {
        User user = new User();
        user.setUsername(worker.getLogin());
        user.setPassword(worker.getPassword());
        user.setActive(true);
        return userRepository.save(user);
    }

}
