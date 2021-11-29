package com.voxloud.imageservice.service;

import com.voxloud.imageservice.model.Role;

public interface RoleService {
    Role getRoleByRoleName(String roleName);

    Role save(Role role);
}
