package com.voxloud.image_service.service;

import com.voxloud.image_service.model.Role;

public interface RoleService {
    Role getRoleByRoleName(String roleName);
    Role save(Role role);
}
