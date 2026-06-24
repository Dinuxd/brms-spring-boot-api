# Boarding Room Management API

A Spring Boot REST API for managing boarding-room listings, bookings, contracts, payments, maintenance requests, users, and roles.

This project demonstrates a layered backend architecture using controllers, services, repositories, JPA entities, DTOs, and SQL Server persistence.

## Features

- User and role management
- Boarding-room listing CRUD
- Booking creation, lookup, and cancellation
- Automatic contract creation for bookings
- Payment record handling
- Maintenance request creation and status updates
- SQL Server database integration with Spring Data JPA

## Tech Stack

- Java 17+
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Microsoft SQL Server
- Maven

## Project Structure

```text
.
|-- src/main/java/com/api/brms/
|   |-- controller/
|   |-- dto/
|   |-- entity/
|   |-- repository/
|   |-- service/
|   `-- BrmsApplication.java
|-- src/main/resources/
|   |-- application.properties
|   `-- application.example.properties
|-- pom.xml
|-- mvnw
|-- mvnw.cmd
`-- README.md
```

## Configuration

The application reads database settings from environment variables. This keeps local credentials out of the public repository.

Required variables:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

Optional variables:

```text
JPA_DDL_AUTO
JPA_SHOW_SQL
CORS_ALLOWED_ORIGINS
```

Example PowerShell setup:

```powershell
$env:DB_URL="jdbc:sqlserver://localhost\SQLEXPRESS02:1433;databaseName=BRMS_DB;encrypt=true;trustServerCertificate=true"
$env:DB_USERNAME="sa"
$env:DB_PASSWORD="your_password"
```

You can also use `src/main/resources/application.example.properties` as a reference for local setup.

## How to Run

Using Maven Wrapper:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The API starts on:

```text
http://localhost:8080
```

## How to Test

The test profile uses an in-memory H2 database, so tests can run without SQL Server:

```bash
./mvnw test
```

On Windows PowerShell:

```powershell
.\mvnw.cmd test
```

## Main Endpoints

```text
GET    /api/users
POST   /api/users
GET    /api/users/{id}
PUT    /api/users/{id}
DELETE /api/users/{id}

GET    /api/roles
POST   /api/roles
GET    /api/roles/{id}
PUT    /api/roles/{id}
DELETE /api/roles/{id}

GET    /api/listings
POST   /api/listings
GET    /api/listings/{id}
PUT    /api/listings/{id}
DELETE /api/listings/{id}

POST   /api/bookings
GET    /api/bookings/{id}
GET    /api/bookings/user/{userId}
GET    /api/bookings/listing/{listingId}
POST   /api/bookings/{id}/cancel

POST   /api/payments/booking/{bookingId}
GET    /api/payments/booking/{bookingId}
```

## Sample Request

Create a listing:

```json
{
  "title": "Single Room Near Campus",
  "description": "Furnished room with shared kitchen and Wi-Fi.",
  "location": "Colombo",
  "price": 25000,
  "available": true
}
```

Create a user:

```json
{
  "name": "Dinupa Devinda",
  "email": "dinupa@example.com",
  "password": "demo-password",
  "roleIds": [1]
}
```

## Notes

This is an academic backend API project. Authentication, password hashing, validation, and production-grade error handling should be added before using it in a real deployment.
