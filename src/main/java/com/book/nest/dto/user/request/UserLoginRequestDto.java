package com.book.nest.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @Email
    private String email;
    @NotEmpty
    private String password;
}
