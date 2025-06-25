package com.api.brms.controller;

import com.api.brms.entity.Role;
import com.api.brms.repository.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleRepository roleRepo;

    public RoleController(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role newRole) {
        Role saved = roleRepo.save(newRole);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> found = roleRepo.findById(id);
        return found
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(
            @PathVariable Long id,
            @RequestBody Role roleDetails
    ) {
        return roleRepo.findById(id)
                .map(role -> {
                    role.setName(roleDetails.getName());
                    Role updated = roleRepo.save(role);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        if (!roleRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        roleRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
