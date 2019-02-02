package com.library.service.interfaces;

import com.library.model.User;
import com.library.model.UserRole;
import com.library.model.Worker;

import java.util.List;
import java.util.Optional;

public interface WorkerService {
    List<Worker> showWorkers();
    Worker registerWorker(Worker worker);
    User addWorkerToUserDatabase(Worker worker);
    UserRole addRoleToWorker(User user);
    void editPassword(Integer id);
    Optional<Worker> getById(Integer workerId);
    void deleteById(Integer id);
}
