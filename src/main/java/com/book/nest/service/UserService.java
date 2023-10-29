package com.book.nest.service;

import com.book.nest.dto.user.request.UserRegistrationRequestDto;
import com.book.nest.dto.user.response.UserResponseDto;
import com.book.nest.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto userDto) throws RegistrationException;
}
