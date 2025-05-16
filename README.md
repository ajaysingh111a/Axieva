# Axieva

Axieva is a Spring Boot application that implements JWT-based authentication, role-based access control, and MySQL persistence. This project includes an Admin-restricted user creation endpoint and login functionality.

## 🛠 Tech Stack

- Java 17+
- Spring Boot 3+
- Spring Security 6+ (JWT-based)
- MySQL
- Maven

## 🚀 Features

- User registration and login
- Password hashing with BCrypt
- Role-based access (only admins can create users)
- JWT token generation and authentication
- MySQL as the primary database

## 🔐 Authentication

- Passwords are hashed using `BCryptPasswordEncoder`
- JWT tokens are issued on login and verified on each request
- User roles are used to restrict endpoints (e.g., `@PreAuthorize("hasRole('ADMIN')")`)

## 👤 Sample Admin User

An admin user is created using a `CommandLineRunner`:

```java
Users user = new Users("admin@gmail.com", passwordEncoder.encode("admin123"), UserType.ADMIN);
userRepository.save(user);
