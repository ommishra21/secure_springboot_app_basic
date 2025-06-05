# secure_springboot_app_basic
A simple demonstration of AppSec using springboot

What You Need
Java 17 or newer

Maven 3.6+

An IDE you like (IntelliJ, VSCode, Eclipse, etc.)

A modern web browser

Quick Setup
Clone or download this repo

Open your terminal and navigate to the project folder

Run the app with:
mvn spring-boot:run
Open your browser and go to: http://localhost:8080

How To Use
1. User Registration
Register new users with email and password (minimum 8 characters)

Passwords are securely hashed with BCrypt

Email must be unique

Forms protected from CSRF attacks

Try it: Visit http://localhost:8080/register

2. User Login & Authentication
Login with email and password

Secure session handling (prevents fixation and enforces single session)

CSRF tokens on all sensitive requests

Friendly error messages on login failures

Try it: Visit http://localhost:8080/login

3. Role-Based Access Control
Two roles: USER (default) and ADMIN

/user/** pages require USER role

/admin/** pages require ADMIN role

Public pages: /register, /login

Want admin access?

Open the H2 console at http://localhost:8080/h2-console (dev only)

Connect using:

JDBC URL: jdbc:h2:mem:appsecdb

User: sa

Password: (leave blank)

Run SQL:
INSERT INTO user_roles (user_id, role) VALUES (1, 'ROLE_ADMIN');
Now you can access admin pages like /admin/home

4. Session Management
New session ID after login (fixation protection)

Only 1 active session per user at a time

Secure logout invalidates session

Cookies use HttpOnly flag (and Secure flag if HTTPS enabled)

Try logging in from two browsers — the first session will expire!

5. H2 Database Console (Dev Only)
Access it at http://localhost:8080/h2-console

Great for viewing users, roles, and testing queries

Disabled in production for security

Why This App is Secure
Passwords: Stored only as salted BCrypt hashes

CSRF Protection: Enabled by default on all forms

Input Validation: Server-side checks for email and password format

XSS Protection: Outputs escaped by default

Security Headers: Includes CSP, X-Frame-Options, HSTS, and others

SQL Injection Protection: Uses Spring Data JPA with parameterized queries

How to Test Security Features
Remove CSRF token from a form — submission will fail

Try accessing /admin/home without admin rights — should get 403 error

Try logging in from multiple browsers — earlier sessions expire

Attempt invalid registration (bad email, short password, duplicate email) — validation errors appear


