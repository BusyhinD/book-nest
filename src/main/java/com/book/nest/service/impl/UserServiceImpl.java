package com.book.nest.service.impl;

import com.book.nest.dto.user.request.UserRegistrationRequestDto;
import com.book.nest.dto.user.response.UserResponseDto;
import com.book.nest.exception.RegistrationException;
import com.book.nest.mapper.UserMapper;
import com.book.nest.model.Role;
import com.book.nest.model.User;
import com.book.nest.repository.UserRepository;
import com.book.nest.service.RoleService;
import com.book.nest.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userDto)
            throws RegistrationException {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with this email already exists");
        }
        User user = userMapper.toModel(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleService.getByRoleName(Role.RoleName.USER)));
        return userMapper.toDto(userRepository.save(user));
    }
}
