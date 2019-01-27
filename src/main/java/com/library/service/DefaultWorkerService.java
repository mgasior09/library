package com.library.service;

import com.library.model.User;
import com.library.model.UserRole;
import com.library.model.Worker;
import com.library.repository.interfaces.UserRepository;
import com.library.repository.interfaces.UserRoleRepository;
import com.library.repository.interfaces.WorkerRepository;
import com.library.service.interfaces.UserRoleService;
import com.library.service.interfaces.WorkerService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DefaultWorkerService implements WorkerService{
    private final WorkerRepository workerRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public DefaultWorkerService(WorkerRepository workerRepository, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
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

    @Override
    public UserRole addRoleToUser(User user) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRoleName("ROLE_WORKER");
        return userRoleRepository.save(userRole);
    }
}
