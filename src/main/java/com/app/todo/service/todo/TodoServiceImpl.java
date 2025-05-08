package com.app.todo.service.todo;

import com.app.todo.dto.todo.TodoCreateDTO;
import com.app.todo.dto.todo.TodoResponseDTO;
import com.app.todo.dto.todo.TodoUpdateDTO;
import com.app.todo.entity.Todo;
import com.app.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private TodoResponseDTO todoResponseDTO(Todo todo) {
        return new TodoResponseDTO(todo.getId(), todo.getTitle(), todo.getDescription(), todo.isCompleted());
    }

    @Override
    public TodoResponseDTO createTodo(TodoCreateDTO todoCreateDTO) {
        Todo todo = Todo.builder()
                .title(todoCreateDTO.title().trim())
                .description(todoCreateDTO.description() != null ? todoCreateDTO.description().trim() : null)
                .completed(false)
                .build();
        return todoResponseDTO(todoRepository.save(todo));
    }

    @Override
    public List<TodoResponseDTO> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(this::todoResponseDTO)
                .toList();
    }

    @Override
    public TodoResponseDTO getTodoById(Long id) {
        return todoRepository.findById(id).map(this::todoResponseDTO)
                .orElseThrow(() -> new RuntimeException("Todo not found."));
    }

    @Override
    public TodoResponseDTO updateTodo(Long id, TodoUpdateDTO todoUpdateDTO) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found."));

        Todo updatedTodo = Todo.builder()
                .id(todo.getId())
                .title(Optional.ofNullable(todoUpdateDTO.title())
                        .map(String:: trim)
                        .filter(s -> !s.isEmpty())
                        .orElse(todo.getTitle())
                )
                .description(Optional.ofNullable(todoUpdateDTO.description())
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .orElse(todo.getDescription())
                )
                .completed(Optional.ofNullable(todoUpdateDTO.completed())
                        .orElse(todo.isCompleted())
                )
                .build();
        return todoResponseDTO(todoRepository.save(updatedTodo));
    }

    @Override
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) throw new RuntimeException("Todo not found.");
        todoRepository.deleteById(id);
    }
}
