package com.app.todo.dto.todo;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TodoUpdateDTO(
        @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters.")
        @Pattern(regexp = ".*\\S.*", message = "Title cannot be only whitespace.")
        String title,

        @Size(max = 255, message = "Description can be at most 255 characters.")
        String description,

        Boolean completed
) {
}
