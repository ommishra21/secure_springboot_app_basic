Prerequisites
Java 17 or higher

Maven 3.6 or higher

Any Java IDE (IntelliJ IDEA, VSCode, Eclipse, etc.)

Web browser

Setup Instructions
Clone or download this repository.

Open a terminal and navigate to the project directory.

Run the application using:

mvn spring-boot:run
Open your web browser and go to: http://localhost:8080

Core Features and Usage
User Registration
URL: /register

Password must be at least 8 characters.

Email addresses must be unique.

Passwords are hashed with BCrypt before storage.

Form submissions include CSRF protection.

How to use: Navigate to http://localhost:8080/register to create a new account.

Authentication and Session Management
URL: /login

Secure login with email and password.

Session fixation protection with new session ID after login.

Single concurrent session per user.

CSRF token validation on sensitive requests.

HttpOnly cookies for session security.

How to use: Access http://localhost:8080/login to sign in.

Role-Based Access Control (RBAC)
Roles: ROLE_USER (default), ROLE_ADMIN (admin privileges)

Access restrictions:

/user/** requires ROLE_USER

/admin/** requires ROLE_ADMIN

/register and /login are public

Admin Setup:
Use the H2 database console (dev only) at http://localhost:8080/h2-console to assign admin roles via SQL.

H2 Database Console (Development Only)
URL: /h2-console

Access credentials:

JDBC URL: jdbc:h2:mem:appsecdb

Username: sa

Password: (leave empty)

Use this console to view and manage database tables during development.

Security Highlights
Passwords stored using salted BCrypt hashing.

CSRF protection enabled by default on all forms.

Server-side input validation for email and password.

Output escaping to prevent XSS attacks.

Security headers such as Content Security Policy and HSTS applied.

SQL Injection prevention via Spring Data JPA and parameterized queries.

Testing Security Features
Remove CSRF token from form and observe submission failure.

Attempt to access admin URLs without the admin role results in access denied.

Test single session enforcement by logging in from multiple browsers.

Validate input errors when submitting invalid registration data.

Production Considerations
Before deploying to production:

Replace H2 with a production database (PostgreSQL, MySQL).

Enable HTTPS with valid SSL certificates.

Disable the H2 console.

Configure secure cookie flags and HTTP security headers.

Implement rate limiting and account lockout mechanisms.

Consider multi-factor authentication for sensitive accounts.
