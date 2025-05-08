# Spring Boot JWT Security Application

This is a Spring Boot application built with Java 21. It features user authentication using Spring Security with JWT — supporting both headers and cookies for token transfer.

## Features

- User registration and login
- JWT authentication (access + refresh tokens is not there)
- Supports tokens via HTTP headers and HTTP-only cookies
- Secure endpoints protected by Spring Security
- Maven-based project
- RESTful APIs

## Technologies Used

- Java 21
- Spring Boot
- Spring Security
- Spring Web
- Spring Data JPA
- JWT (Java Web Tokens)
- Maven
- H2 (configurable)
- Lombok

## Getting Started

### Prerequisites

- Java 21
- Maven 3.x
- IDE (IntelliJ, Eclipse, etc.)
- Git

### Running the App

```bash
mvn spring-boot:run
