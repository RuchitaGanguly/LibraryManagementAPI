# Library Management REST API

A RESTful Library Management System developed using **Spring Boot, Spring Data JPA, Hibernate, and MySQL**. This application manages books, students, and book issuing and returning operations.

## 1. Technologies Used

- Java 17
- Spring Boot 3.5.7
- Spring Data JPA
- Hibernate
- MySQL 8
- Maven
- Postman
- Git and GitHub

## 2. Project Features

### Book Management
- Add a new book.
- Retrieve all books.
- Retrieve a book by ID.
- Update book details.
- Delete a book.
- Search books by category.
- Prevent duplicate ISBN entries.

### Student Management
- Register a student.
- Retrieve all students.
- Retrieve a student by ID.
- Update student details.
- Delete a student.
- Prevent duplicate email addresses.
- Validate email and phone number.

### Book Issue Management
- Issue books to students.
- Retrieve all issue records.
- Retrieve issues by student ID.
- Return issued books.
- Retrieve overdue books.

## 3. Business Rules

- A book cannot be issued if no copies are available.
- A student and book must exist before issuing a book.
- A student can have a maximum of 3 active book issues.
- Each issued book has a due date of 14 days after the issue date.
- Available book copies decrease when a book is issued.
- Available book copies increase when a book is returned.
- A book cannot be returned more than once.
- Overdue books are identified based on their due dates.

## 4. Project Structure

```text
LibraryManagementAPI
│
├── src/main/java/LibraryManagementAPI
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── exception
│   ├── repository
│   └── service
│
├── src/main/resources
│   └── application.properties
│
├── src/test/java
├── pom.xml
└── README.md
```

## 5. Database Configuration

Database used: **MySQL**

Create the database using:

```sql
CREATE DATABASE library_db;
```

Configure the database in `application.properties`:

```properties
spring.application.name=LibraryManagementAPI

spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8081
```

Set the `DB_PASSWORD` environment variable to your MySQL password before running the application.

**Note:** Database passwords and other sensitive credentials should never be committed to GitHub.

## 6. How to Run the Project

### Prerequisites

- Java 17 or later
- MySQL 8
- Maven

### Steps

1. Clone the repository:

```bash
git clone https://github.com/RuchitaGanguly/LibraryManagementAPI.git
```

2. Open the project in Eclipse or another Java IDE.

3. Create the MySQL database:

```sql
CREATE DATABASE library_db;
```

4. Configure the `DB_PASSWORD` environment variable.

5. Update the Maven dependencies.

6. Run `LibraryManagementApplication.java` as a Spring Boot application.

The application runs at:

```text
http://localhost:8081
```

## 7. API Endpoints

### Book APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/books` | Add a book |
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get book by ID |
| PUT | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Delete a book |
| GET | `/api/books/category/{category}` | Get books by category |

### Student APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/students` | Add a student |
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| PUT | `/api/students/{id}` | Update a student |
| DELETE | `/api/students/{id}` | Delete a student |

### Book Issue APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/issues` | Issue a book |
| GET | `/api/issues` | Get all issue records |
| GET | `/api/issues/student/{studentId}` | Get issues by student |
| PUT | `/api/issues/return/{issueId}` | Return a book |
| GET | `/api/issues/overdue` | Get overdue books |

### Sample Request: Add Book

**POST** `/api/books`

```json
{
  "title": "Java Programming",
  "author": "James Gosling",
  "isbn": "9781234567890",
  "category": "Programming",
  "totalCopies": 5
}
```

### Sample Request: Add Student

**POST** `/api/students`

```json
{
  "studentName": "Rahul Sharma",
  "email": "rahul@example.com",
  "department": "CSE",
  "phoneNumber": "9876543210"
}
```

### Sample Request: Issue Book

**POST** `/api/issues`

```json
{
  "studentId": 1,
  "bookId": 1
}
```

## 8. Exception Handling

The application handles exceptions using a global exception handler (`@RestControllerAdvice`).

| HTTP Status | Description |
|---|---|
| 200 OK | Request successful |
| 201 Created | Resource created successfully |
| 400 Bad Request | Invalid request or business rule violation |
| 404 Not Found | Resource not found |
| 409 Conflict | Duplicate ISBN or email |

## 9. API Testing

All REST APIs can be tested using Postman.

The submission includes:
- Postman collection containing API requests.
- Screenshots of successful API responses.
- Screenshots of validation and exception handling tests.

## 10. GitHub Repository

**Repository:** https://github.com/RuchitaGanguly/LibraryManagementAPI

### Git Branches

- `master` – Initial project branch.
- `development` – Development branch.
- `feature/book-management` – Book Management feature branch.

The Book Management feature branch has been merged into the development branch.

---

**Developed as part of the Spring Boot Practical Assignment – Library Management REST API.**