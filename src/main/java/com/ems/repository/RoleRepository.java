package com.ems.repository;

import com.ems.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findRoleByName(String username);
}
