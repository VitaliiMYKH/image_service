package com.voxloud.imageservice.service.impl;

import com.voxloud.imageservice.exception.DataProcessingException;
import com.voxloud.imageservice.model.Role;
import com.voxloud.imageservice.repository.RoleRepository;
import com.voxloud.imageservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleRepository.getRoleByRoleName(roleName)
                .orElseThrow(() -> new DataProcessingException("Can`t "
                        + "get role with role name: " + roleName));
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
