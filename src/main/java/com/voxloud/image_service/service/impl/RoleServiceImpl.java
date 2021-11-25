package com.voxloud.image_service.service.impl;

import com.voxloud.image_service.exception.DataProcessingException;
import com.voxloud.image_service.model.Role;
import com.voxloud.image_service.repository.RoleRepository;
import com.voxloud.image_service.service.RoleService;
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
                .orElseThrow(() -> new DataProcessingException("Can`t get role with role name: " + roleName));
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
