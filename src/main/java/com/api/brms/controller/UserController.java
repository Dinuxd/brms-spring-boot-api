package com.api.brms.controller;

import com.api.brms.dto.UserRequest;
import com.api.brms.dto.UserResponse;
import com.api.brms.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "${app.cors.allowed-origins:*}")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) { this.userService = userService; }

    /* ---------- POST /api/users ---------- */
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest req) {
        return ResponseEntity.status(201).body(userService.createUser(req));
    }

    /* ---------- GET  /api/users ---------- */
    @GetMapping
    public List<UserResponse> all() {
        return userService.findAllUsers();
    }

    /* ---------- GET  /api/users/{id} ---------- */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> one(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    /* ---------- PUT  /api/users/{id} ---------- */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,
                                               @RequestBody UserRequest req) {
        return ResponseEntity.ok(userService.updateUser(id, req));
    }

    /* ---------- DELETE /api/users/{id} ---------- */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
