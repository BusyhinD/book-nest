package com.book.nest.service.impl;

import com.book.nest.model.Role;
import com.book.nest.repository.RoleRepository;
import com.book.nest.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getByRoleName(Role.RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
