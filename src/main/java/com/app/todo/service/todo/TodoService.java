package com.app.todo.service.todo;

import com.app.todo.dto.todo.TodoCreateDTO;
import com.app.todo.dto.todo.TodoResponseDTO;
import com.app.todo.dto.todo.TodoUpdateDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface TodoService {

    TodoResponseDTO createTodo(@Valid TodoCreateDTO todoCreateDTO);

    List<TodoResponseDTO> getAllTodos();

    TodoResponseDTO getTodoById(Long id);

    TodoResponseDTO updateTodo(Long id, TodoUpdateDTO todoUpdateDTO);

    void deleteTodo(Long id);
}
