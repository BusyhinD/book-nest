package com.book.nest.mapper;

import com.book.nest.config.MapperConfig;
import com.book.nest.dto.user.request.UserRegistrationRequestDto;
import com.book.nest.dto.user.response.UserResponseDto;
import com.book.nest.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto userDto);
}
