package com.voxloud.imageservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.voxloud.imageservice.exception.DataProcessingException;
import com.voxloud.imageservice.model.Role;
import com.voxloud.imageservice.repository.RoleRepository;
import com.voxloud.imageservice.service.RoleService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RoleServiceImplTest {
    private RoleRepository roleRepository;
    private RoleService roleService;
    private Role unsavedRole;
    private Role savedRole;
    private String roleName;
    private Long roleId;

    @BeforeEach
    void setUp() {
        roleRepository = Mockito.mock(RoleRepository.class);
        roleName = "testRoleName";
        roleId = 1L;
        roleService = new RoleServiceImpl(roleRepository);
        unsavedRole = new Role();
        unsavedRole.setRoleName(roleName);
        savedRole = new Role();
        savedRole.setId(roleId);
        savedRole.setRoleName(roleName);
    }

    @Test
    void getRoleByRoleName_Ok() {
        Mockito.when(roleRepository.getRoleByRoleName(roleName)).thenReturn(Optional.of(savedRole));
        Role actual = roleService.getRoleByRoleName(roleName);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(savedRole, actual);
    }

    @Test
    void getRoleByRoleName_NotOk() {
        String invalidRoleName = "invalidRoleName";
        Mockito.when(roleRepository.getRoleByRoleName(roleName)).thenReturn(Optional.of(savedRole));
        try {
            roleService.getRoleByRoleName(invalidRoleName);
        } catch (DataProcessingException e) {
            Assertions.assertEquals("Can`t get role with role name: " + invalidRoleName, e.getMessage());
            return;
        }
        Assertions.fail("Expected to receive DataProcessingException");
    }

    @Test
    void save_Ok() {
        Mockito.when(roleRepository.save(unsavedRole)).thenReturn(savedRole);
        Role actual = roleService.save(unsavedRole);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(savedRole, actual);
    }
}