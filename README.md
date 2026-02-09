
---

# Car Rental Service Management

## Project Overview

**Car Rental Service** is a full-stack web application designed to streamline the vehicle rental process. Built with the **Spring Boot** framework, it manages everything from car inventory and customer registration to rental transactions and security.

The project features a layered backend architecture and a responsive frontend using modern web technologies.

---

## Project Features

* **User Management:** Registration and authentication for customers and administrators.
* **Security Suite:** Includes email validation, password encryption, and token-based security.
* **Car Inventory:** Dynamic management of the vehicle fleet within the `car` model.
* **Rental Workflow:** Specialized logic for processing rentals and tracking vehicle availability.
* **Web Interface:** A complete frontend with dedicated CSS for styling and JS for interactivity.
* **Data Persistence:** Integrated repository layer for managing persistent car and user data.

---

## Project Structure

The project follows a standard Maven directory structure, separating the Spring Boot backend logic from the web-based resources.

```text
ProjectRoot/
│
├── src/main/java/com/rentaride/carrental/
│   ├── config/            # Security and app configurations
│   ├── controller/        # REST controllers and routing
│   ├── cookie/            # Session and cookie management
│   ├── error/             # Exception handling
│   ├── model/             # Data models (AppUser, Car, Customer, Rental)
│   │   └── dto/           # Data Transfer Objects
│   ├── registration/      # Signup logic, EmailSender, and EmailValidator
│   ├── repository/        # Data access layer
│   ├── service/           # Business logic implementation
│   └── CarRentalApplication.java  # Main entry point
│
├── src/main/resources/
│   ├── static/            # Frontend assets
│   │   ├── css/           # Custom stylesheets
│   │   ├── csv/           # Data files
│   │   ├── images/        # UI and car images
│   │   └── js/            # Client-side scripting
│   ├── templates/         # HTML templates
│   └── application.properties # Spring Boot configuration
│
└── pom.xml                # Project dependencies

```

---

## Technologies Used

* **Java SE & Spring Boot:** Core backend framework.
* **Spring Security:** For user authentication and email validation.
* **Spring Data JPA:** For database interaction and repositories.
* **Maven:** Dependency management and build tool.
* **Frontend:** HTML5, CSS3, and JavaScript.

---

## How to Run the Application

1. **Clone the project** and open it in your preferred IDE (IntelliJ IDEA recommended).
2. **Ensure JDK 17+** is installed and configured in your environment.
3. **Configure Database:** Update the `application.properties` file with your database credentials.
4. **Run the App:** Execute the `CarRentalApplication.java` file.
5. **Access:** Navigate to `http://localhost:8080` in your web browser.

---

## Documentation

* **Backend:** Logic is organized by domain (AppUser, Car, Customer) within the `model` and `service` packages.
* **Validation:** Account creation requires valid email verification via the `registration` module.
* **Resources:** Static assets and data files are located in the `resources/static` directory.

---
