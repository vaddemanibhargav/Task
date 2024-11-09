package com.example.task_manager.service;

import com.example.task_manager.entity.Task;
import com.example.task_manager.exception.ResourceNotFoundException;
import com.example.task_manager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now()); return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        taskRepository.delete(task);
    }




}
