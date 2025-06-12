package org.arquitetura.service;

import org.arquitetura.exception.ResourceNotFoundException;
import org.arquitetura.model.Task;
import org.arquitetura.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public List<Task> findAllByUser(Long userId) {
        return taskRepository.findAll().stream()
                .filter(t -> t.getUserId().equals(userId))
                .toList();
    }
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));

    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Long id, Task task) {
        Task taskToUpdate = findById(id);
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        return taskRepository.save(taskToUpdate);
    }
    public void delete(Long id) {
        taskRepository.delete(findById(id));
    }
}
