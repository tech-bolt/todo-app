package com.app.todo.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "First name is required.")
        @Size(max = 25, message = "First name must be at most 25 characters. ")
        String firstName,

        @NotBlank(message = "Last name is required.")
        @Size(max = 25, message = "Last name must be at most 25 characters. ")
        String lastName,

        @Email
        @NotBlank(message = "Email is required.")
        @Size(max = 50, message = "Email must be at most 50 characters. ")
        String email,

        @NotBlank(message = "Password is required.")
        @Size(max = 25, message = "Password must be at most 25 characters. ")
        String password,

        String confirmPassword
) {
}
