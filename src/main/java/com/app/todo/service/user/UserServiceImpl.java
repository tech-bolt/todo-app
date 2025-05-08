package com.app.todo.service.user;

import com.app.todo.dto.auth.LoginRequest;
import com.app.todo.dto.auth.RegisterRequest;
import com.app.todo.entity.User;
import com.app.todo.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public String login(LoginRequest loginRequest, HttpServletResponse response) {
        try {
            Authentication authenticationRequest =
                    UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email(), loginRequest.password());

            Authentication authenticationResponse =
                    this.authenticationManager.authenticate(authenticationRequest);

            String jwt = jwtService.generateToken(loginRequest.email());
            ResponseCookie cookie = ResponseCookie.from("jwt", jwt)
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Strict")
                    .path("/")
                    .maxAge(60 * 60)
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return "Login successful.";
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid email or password.");
        }

    }

    @Override
    public String register(RegisterRequest registerRequest) {
        if (!registerRequest.password().equals(registerRequest.confirmPassword())) throw new RuntimeException("Passwords do not match.");

        if (userRepository.findByEmail(registerRequest.email()).isPresent()) throw new RuntimeException("User already exists.");

        User user = User.builder()
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .build();

        userRepository.save(user);
        return "User registered successfully.";
    }
}
