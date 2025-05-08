package com.app.todo.controller;

import com.app.todo.dto.todo.TodoCreateDTO;
import com.app.todo.dto.todo.TodoResponseDTO;
import com.app.todo.dto.todo.TodoUpdateDTO;
import com.app.todo.service.todo.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDTO> createTodo(@Valid @RequestBody TodoCreateDTO todoCreateDTO) {
        return ResponseEntity.status(201).body(todoService.createTodo(todoCreateDTO));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> getTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> updateTodo(@PathVariable Long id,@Valid @RequestBody TodoUpdateDTO todoUpdateDTO) {
        return ResponseEntity.ok(todoService.updateTodo(id, todoUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo deleted successfully.");
    }
}
