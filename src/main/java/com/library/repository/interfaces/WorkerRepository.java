package com.library.repository.interfaces;

import com.library.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    List<Worker> findAll();
    void deleteById(Integer id);
    Optional<Worker> findById(Integer id);
}
