package com.voxloud.image_service.repository;

import com.voxloud.image_service.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> getRoleByRoleName(String roleName);
}
