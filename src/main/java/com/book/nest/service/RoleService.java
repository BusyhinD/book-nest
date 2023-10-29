package com.book.nest.service;

import com.book.nest.model.Role;

public interface RoleService {
    Role getByRoleName(Role.RoleName roleName);
}
