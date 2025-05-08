package com.app.todo.dto.todo;

public record TodoResponseDTO(
        Long id,
        String title,
        String description,
        boolean completed
) {
}
