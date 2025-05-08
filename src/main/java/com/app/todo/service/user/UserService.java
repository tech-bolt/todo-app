package com.app.todo.service.user;

import com.app.todo.dto.auth.LoginRequest;
import com.app.todo.dto.auth.RegisterRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

public interface UserService {
    String login(@Valid LoginRequest loginRequest, HttpServletResponse response);

    String register(@Valid RegisterRequest registerRequest);
}
