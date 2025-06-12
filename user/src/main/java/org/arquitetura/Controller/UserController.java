package org.arquitetura.Controller;

import org.arquitetura.dto.ApiResponse;
import org.arquitetura.model.User;
import org.arquitetura.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> all(){
        var data = userService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(200, null, data));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<User>> getOne(@PathVariable Long id){
        var user = userService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, null, user));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> create(@RequestBody User user){
        var userCreated = userService.create(user);
        return ResponseEntity.status(201).body(new ApiResponse<>(201, null, userCreated));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<User>> update(@PathVariable Long id, @RequestBody User user){
        var userUpdated = userService.update(id, user);
        return ResponseEntity.ok(new ApiResponse<>(200, null, userUpdated));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.status(204).body(new ApiResponse<>(204, null, null));
    }
}
