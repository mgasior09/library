package com.library.service.interfaces;

import com.library.model.User;
import com.library.model.UserRole;
import com.library.model.Worker;

import java.util.List;

public interface WorkerService {
    List<Worker> showWorkers();
    Worker registerWorker(Worker worker);
    User addWorkerToUserDatabase(Worker worker);
    UserRole addRoleToUser(User user);
}
