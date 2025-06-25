package com.api.brms.service.impl;

import com.api.brms.entity.Role;
import com.api.brms.repository.RoleRepository;
import com.api.brms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

    @Override
    public Role updateRole(Long id, Role role) {
        Role existing = getRoleById(id);
        existing.setName(role.getName());
        return roleRepository.save(existing);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
