# Secure Spring Boot Web Application - Complete Guide
---

![Screenshot 2025-06-05 155857](https://github.com/user-attachments/assets/bc37c6f5-f1df-4be9-87da-9050da1fa46b)

---

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Your favorite IDE (IntelliJ IDEA, VSCode, Eclipse)
- Web browser

### Quick Setup
1. Clone/download the project
2. Navigate to project directory
3. Run: `mvn spring-boot:run`
4. Open browser: `http://localhost:8080`

## Core Features & How to Use Them

### 1. User Registration System
**URL**: `/register`

**Features**:
- Secure password validation (minimum 8 characters)
- Email uniqueness validation
- Password automatically hashed with BCrypt
- CSRF protection on form submission

**How to Use**:
1. Navigate to `http://localhost:8080/register`
2. Enter valid email and password (8+ characters)
3. Submit form - password is automatically salted and hashed
4. Redirected to login page with success message

### 2. Authentication System
**URL**: `/login`

**Features**:
- Secure login with email/password
- Session management with security headers
- CSRF token validation
- Session fixation protection
- Single session enforcement

**How to Use**:
1. Go to `http://localhost:8080/login`
2. Enter registered email and password
3. Successfully logged users redirect to `/user/home`
4. Failed attempts show generic error messages (security best practice)

### 3. Role-Based Access Control (RBAC)

**User Roles**:
- `ROLE_USER`: Default role for registered users
- `ROLE_ADMIN`: Administrative privileges

**Access Levels**:
- `/user/**` - Requires ROLE_USER
- `/admin/**` - Requires ROLE_ADMIN
- `/register`, `/login` - Public access

**How to Test Admin Access**:
1. Access H2 console: `http://localhost:8080/h2-console`
2. Connect with: URL: `jdbc:h2:mem:appsecdb`, User: `sa`, Password: (empty)
3. Run SQL: `INSERT INTO user_roles (user_id, role) VALUES (1, 'ROLE_ADMIN');`
4. Now user can access `/admin/home`

### 4. Session Management
**Features**:
- Session fixation protection (new session ID after login)
- Maximum 1 concurrent session per user
- Secure logout with session invalidation
- HttpOnly and Secure cookies (when HTTPS enabled)

**How to Test**:
1. Login from one browser
2. Try logging in from another browser/incognito
3. First session gets expired automatically

### 5. Database Console (Development Only)
**URL**: `/h2-console`

**Security Note**: Only enabled in development mode

**How to Use**:
1. Navigate to `http://localhost:8080/h2-console`
2. Use connection details:
   - JDBC URL: `jdbc:h2:mem:appsecdb`
   - User Name: `sa`
   - Password: (leave empty)
3. View users table: `SELECT * FROM USERS;`
4. View roles: `SELECT * FROM USER_ROLES;`

## Security Features Explained

### 1. Password Security
- **BCrypt Hashing**: Passwords stored with salt and strong hashing
- **Validation**: Minimum 8 characters required
- **Never Stored Plain**: Original passwords never saved

### 2. CSRF Protection
- **Automatic**: Enabled by default on all POST/PUT/DELETE requests
- **Token Validation**: Each form includes hidden CSRF token
- **Thymeleaf Integration**: `th:action` automatically includes tokens

### 3. Input Validation
- **Server-Side**: Using Jakarta Validation annotations
- **Email Format**: Valid email format required
- **XSS Prevention**: Thymeleaf escapes output by default

### 4. Security Headers
**Implemented Headers**:
- Content Security Policy (CSP)
- X-Frame-Options: SAMEORIGIN
- HTTP Strict Transport Security (HSTS)
- X-Content-Type-Options: nosniff

**View Headers**: Use browser dev tools -> Network tab -> Response Headers

### 5. SQL Injection Protection
- **JPA Repositories**: Automatic parameterized queries
- **No Raw SQL**: All database access through Spring Data JPA

## Testing the Security Features

### Test CSRF Protection
1. Disable JavaScript in browser
2. Try submitting registration form
3. Should work (CSRF token included)
4. Manually remove CSRF token from form HTML
5. Form submission should fail

### Test Session Security
1. Login successfully
2. Open browser dev tools -> Application/Storage -> Cookies
3. Verify JSESSIONID has HttpOnly flag
4. Copy session ID, logout, try accessing with old session ID
5. Should redirect to login

### Test Access Control
1. Access `/admin/home` without admin role -> 403 Forbidden
2. Access `/user/home` without login -> Redirect to login
3. Login and access appropriate pages based on role

### Test Input Validation
1. Try registering with invalid email format
2. Try password less than 8 characters
3. Try registering with existing email
4. All should show appropriate validation errors

## Monitoring & Logging

### Application Logs
- Authentication success/failure events
- Access denied attempts
- Security exceptions

### View Logs
- Console output shows authentication events
- Failed login attempts logged for monitoring

