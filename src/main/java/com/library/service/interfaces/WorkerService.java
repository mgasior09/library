package com.library.service.interfaces;

import com.library.model.Worker;
import org.hibernate.jdbc.Work;

import java.util.List;

public interface WorkerService {
    List<Worker> showWorkers();
    Worker registerWorker(Worker worker);
}
