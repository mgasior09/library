package com.library.service;

import com.library.model.Worker;
import com.library.repository.interfaces.WorkerRepository;
import com.library.service.interfaces.WorkerService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DefaultWorkerService implements WorkerService {
    private final WorkerRepository workerRepository;

    public DefaultWorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
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
}
