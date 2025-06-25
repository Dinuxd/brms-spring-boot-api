package com.api.brms.service;

import com.api.brms.entity.Role;
import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    Role updateRole(Long id, Role role);
    void deleteRole(Long id);
}
