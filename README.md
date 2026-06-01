# Inventory Management System

A web-based Inventory Management System developed as a 2nd Year 1st Semester Software Engineering group project. The system is designed to manage inventory-related operations such as products, brands, categories, stock records, and user authentication through a structured full-stack application.

## Overview

This project focuses on building a practical inventory management solution using a Spring Boot backend and a web-based frontend. It demonstrates important software engineering concepts such as MVC architecture, REST API development, database integration, authentication, validation, and layered application design.

## Features

* Product management
* Brand management
* Category management
* Inventory/stock data handling
* User authentication using JWT
* RESTful API structure
* Database persistence with MySQL
* Input validation
* PDF report generation
* Clean frontend interface using HTML, CSS, and JavaScript

## Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* MySQL
* JWT Authentication
* Maven
* OpenPDF

### Frontend

* HTML
* CSS
* JavaScript

## Project Structure

```text
SE-2-group-project/
│
├── inventory-system/
│   ├── src/
│   │   └── main/
│   │       ├── java/org/example/
│   │       │   ├── controller/
│   │       │   ├── dto/
│   │       │   ├── exception/
│   │       │   ├── model/
│   │       │   ├── repository/
│   │       │   ├── service/
│   │       │   └── Main.java
│   │       │
│   │       └── resources/
│   │           ├── static/
│   │           └── application.properties
│   │
│   └── pom.xml
│
└── README.md
```

## Getting Started

### Prerequisites

Make sure you have installed:

* Java 17 or higher
* Maven
* MySQL Server
* Git

## Installation

1. Clone the repository:

```bash
git clone https://github.com/senuja1/SE-2-group-project.git
```

2. Navigate to the project folder:

```bash
cd SE-2-group-project/inventory-system
```

3. Create a MySQL database:

```sql
CREATE DATABASE inventory_system;
```

4. Configure the database connection in:

```text
src/main/resources/application.properties
```

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_system
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

5. Run the application:

```bash
mvn spring-boot:run
```

6. Open the application in your browser:

```text
http://localhost:8080
```

## API Architecture

The backend follows a layered architecture:

* **Controller Layer**: Handles HTTP requests and API endpoints
* **Service Layer**: Contains business logic
* **Repository Layer**: Communicates with the database
* **Model Layer**: Represents database entities
* **DTO Layer**: Transfers data between client and server
* **Exception Layer**: Handles application-level errors

## Learning Outcomes

This project helped the team gain practical experience in:

* Building REST APIs with Spring Boot
* Connecting Java applications with MySQL
* Implementing authentication using JWT
* Applying MVC and layered architecture
* Managing frontend and backend integration
* Working with Maven dependencies
* Structuring a group software engineering project

## Contributors

Developed as a group project for the Software Engineering module.

## License

This project was developed for academic purposes.
