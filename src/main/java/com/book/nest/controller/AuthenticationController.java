package com.book.nest.controller;

import com.book.nest.dto.user.request.UserLoginRequestDto;
import com.book.nest.dto.user.request.UserRegistrationRequestDto;
import com.book.nest.dto.user.response.UserLoginResponseDto;
import com.book.nest.dto.user.response.UserResponseDto;
import com.book.nest.exception.RegistrationException;
import com.book.nest.security.AuthenticationService;
import com.book.nest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication management", description = "Endpoints for register and login users")
@RestController
@RequestMapping(value = "/auth", produces = "application/json")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Register a new user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User was created."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request. Incorrect user input data."
                    )
            })
    @PostMapping("/register")
    public UserResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto userRegistrationDto)
            throws RegistrationException {
        return userService.register(userRegistrationDto);
    }

    @Operation(summary = "Login a user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User successfully login."
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Authentication failed: Wrong login or password."
                    )
            })
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto userRequestDto) {
        return authenticationService.authenticate(userRequestDto);
    }
}
