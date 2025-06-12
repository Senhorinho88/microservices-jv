package org.arquitetura.controller;


import org.arquitetura.dto.ApiResponse;
import org.arquitetura.model.Task;
import org.arquitetura.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Task>>> all(@RequestParam Long userId){
        var data = taskService.findAllByUser(userId);
        return ResponseEntity.ok(new ApiResponse<>(200,null,data));

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Task>> create(@RequestBody Task t) {
        var created = taskService.create(t);
        return ResponseEntity.status(201).body(new ApiResponse<>(201, null, created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> update(@PathVariable Long id, @RequestBody Task t) {
        var upd = taskService.update(id, t);
        return ResponseEntity.ok(new ApiResponse<>(200, null, upd));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.status(204).body(new ApiResponse<>(204, null, null));
    }
}