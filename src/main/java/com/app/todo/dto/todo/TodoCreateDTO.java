package com.app.todo.dto.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TodoCreateDTO(
        @NotBlank(message = "Title is required.")
        @Size(max = 100, message = "Title must be at most 100 characters.")
        String title,
        @Size(max = 255, message = "Description can be at most 255 characters.")
        String description
) {
}
