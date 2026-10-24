package com.example.task_api.repository;

import com.example.task_api.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public synchronized List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    public synchronized Task findById(long id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public synchronized Task save(Task task) {
        task.setId(idGenerator.incrementAndGet());
        tasks.add(task);
        return task;
    }

    public synchronized Task update(long id, Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                updatedTask.setId(id);
                tasks.set(i, updatedTask);
                return updatedTask;
            }
        }
        return null;
    }

    public synchronized boolean delete(long id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
}
