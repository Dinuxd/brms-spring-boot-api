package com.api.brms.service.impl;

import com.api.brms.dto.UserRequest;
import com.api.brms.dto.UserResponse;
import com.api.brms.entity.Role;
import com.api.brms.entity.User;
import com.api.brms.repository.RoleRepository;
import com.api.brms.repository.UserRepository;
import com.api.brms.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    /* ---------- CREATE ---------- */
    @Override
    public UserResponse createUser(UserRequest req) {
        User u = new User(req.getName(), req.getEmail(), req.getPassword());
        attachRoles(u, req.getRoleIds());
        User saved = userRepo.save(u);
        return toDto(saved);
    }

    /* ---------- READ ALL ---------- */
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /* ---------- READ ONE ---------- */
    @Override
    @Transactional(readOnly = true)
    public UserResponse findUserById(Long id) {
        return toDto(userRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User " + id + " not found")));
    }

    /* ---------- UPDATE ---------- */
    @Override
    public UserResponse updateUser(Long id, UserRequest req) {
        User u = userRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User " + id + " not found"));
        if (req.getName()  != null) u.setName(req.getName());
        if (req.getEmail() != null) u.setEmail(req.getEmail());
        if (req.getPassword() != null) u.setPassword(req.getPassword());

        if (req.getRoleIds() != null) {   // replace roles
            u.getRoles().clear();
            attachRoles(u, req.getRoleIds());
        }
        return toDto(userRepo.save(u));
    }

    /* ---------- DELETE ---------- */
    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    // ---------------------------------------------------------
    // ---------- private helper methods -----------------------
    // ---------------------------------------------------------

    private void attachRoles(User user, Set<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) return;
        List<Role> roles = roleRepo.findAllById(roleIds);
        if (roles.size() != roleIds.size()) {
            throw new NoSuchElementException("One or more role IDs not found");
        }
        user.getRoles().addAll(roles);
    }

    private UserResponse toDto(User u) {
        UserResponse dto = new UserResponse();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setEmail(u.getEmail());
        Set<String> roleNames = u.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        dto.setRoles(roleNames);
        return dto;
    }
}
