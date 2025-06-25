package com.api.brms.repository;

import com.api.brms.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // you can add custom finder methods here if needed
}
