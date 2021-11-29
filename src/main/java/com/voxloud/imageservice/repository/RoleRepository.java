package com.voxloud.imageservice.repository;

import com.voxloud.imageservice.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> getRoleByRoleName(String roleName);
}
