package com.app.todo;

import com.app.todo.entity.Todo;
import com.app.todo.entity.User;
import com.app.todo.repository.TodoRepository;
import com.app.todo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class TodoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	@Bean
	CommandLineRunner seedData(TodoRepository todoRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Todos
			todoRepository.saveAll(List.of(
					new Todo(null, "Buy groceries", "Milk, eggs, bread", false),
					new Todo(null, "Complete homework", "Math and science chapters", false),
					new Todo(null, "Workout", "Leg day at gym", false),
					new Todo(null, "Read book", "Read 20 pages of a novel", false),
					new Todo(null, "Call Mom", "Weekly call to check in", false)
			));

			// Users with encoded passwords
			userRepository.saveAll(List.of(
					new User(null, "Alice", "Johnson", "alice@example.com", passwordEncoder.encode("password1")),
					new User(null, "Bob", "Smith", "bob@example.com", passwordEncoder.encode("password2")),
					new User(null, "Carol", "White", "carol@example.com", passwordEncoder.encode("password3")),
					new User(null, "David", "Brown", "david@example.com", passwordEncoder.encode("password4")),
					new User(null, "Eve", "Davis", "eve@example.com", passwordEncoder.encode("password5"))
			));
		};
	}
}
