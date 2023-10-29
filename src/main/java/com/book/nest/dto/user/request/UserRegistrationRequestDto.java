package com.book.nest.dto.user.request;

import com.book.nest.lib.validation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@FieldMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Password don't match"
)
public class UserRegistrationRequestDto {
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty
    private String email;
    @Size(min = 6, max = 50)
    private String password;
    @Size(min = 6, max = 50)
    private String repeatPassword;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private String shippingAddress;
}
