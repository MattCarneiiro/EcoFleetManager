⚡ EcoFleet Manager

A Modern Java Console Application for Fleet Management

EcoFleet Manager is a robust, console-based application designed to simulate the management of a rental agency focused on modern electric and autonomous vehicles.

This project was built from scratch using Pure Java (JDK 21), avoiding external frameworks to demonstrate a deep understanding of the language's core features, Object-Oriented Programming (OOP) principles, and modern functional programming paradigms.

🏗️ Architecture & Design

The project follows a clean Layered Architecture, loosely based on the MVC (Model-View-Controller) pattern adapted for a CLI environment. This ensures separation of concerns and maintainability.

src/main/java/org/example/ecofleet/
├── view/        # UI Layer: Handles user input and console output.
├── service/     # Business Layer: "The Brain". Logic, validation, and calculations.
├── repository/  # Data Access Layer: In-memory storage (HashMap) and CRUD operations.
├── model/       # Domain Layer: Entities, Records, and Enums.
└── util/        # Infrastructure: File I/O and helper classes.


🚀 Key Technical Features

1. Modern Java Syntax

Java Records: Used for the FichaTecnica (Technical Spec) to create immutable, concise data carriers for vehicle metadata (Brand, Model, Year, Plate), eliminating boilerplate code.

Switch Expressions: Replaced traditional verbose switch statements with the modern arrow syntax (->) and Pattern Matching for Switch, making the menu control flow and type checking (instanceof) cleaner and safer.

Text Blocks: Used for rendering UI menus, keeping the code readable without messy string concatenation.

2. Object-Oriented Principles

Polymorphism & Inheritance: The system relies on an abstract base class Veiculo extended by specific implementations: CarroAutonomo (Autonomous Car) and CaminhaoEletrico (Electric Truck).

Interfaces: Implemented the Recarregavel (Rechargeable) interface, defining a contract for any entity that consumes energy, decoupling the charging logic from the vehicle hierarchy.

Encapsulation: Strict control over mutable states (like battery levels and mileage) using validation logic within setters.

3. Functional Programming (Streams API)

Instead of traditional loops, the reporting module utilizes Java Streams and Lambdas to perform efficient data processing:

Filtering: Finding vehicles with low battery (<20%).

Sorting: Ordering the fleet by manufacturing year using Comparator.

Mapping: Data transformation pipelines.

4. Data Structures & Performance

HashMap Integration: The repository layer uses a Map<String, Veiculo> where the Key is the License Plate. This ensures O(1) (constant time) complexity for lookups, preventing performance degradation as the fleet grows.

💾 Persistence (File I/O)

The application features a custom persistence engine built with java.nio.file:

CSV Handling: Custom logic to serialize objects into a semicolon-separated format (.csv) and deserialize them back into objects upon startup.

Robust Parsing: Handles specific locales (US/International standards) to prevent NumberFormatException when dealing with floating-point numbers (dots vs. commas).

Error Handling: Implements defensive programming with try-catch blocks to manage I/O exceptions gracefully without crashing the application.

⚡ Concurrency (The "Async" Challenge)

One of the project's highlights is the Non-Blocking Charging System.

CompletableFuture: The "Charge Vehicle" feature runs on a separate thread using the CompletableFuture API.

User Experience: This allows the user to continue navigating the menu, registering new vehicles, or viewing reports while a vehicle charges in the background. The system notifies the user asynchronously when the process is complete.

🛠️ How to Run

Prerequisites: Ensure you have Java 17 or higher installed (Developed on JDK 21).

Clone the repository:

git clone https://github.com/MattCarneiiro/EcoFleetManager.git


Run the application:
Navigate to the src folder and compile the Application.java file, or run it via your favorite IDE (IntelliJ IDEA recommended).

🔮 Future Improvements

Implementation of a real database (MySQL/PostgreSQL) replacing the CSV persistence.

Migration to a REST API using Spring Boot.

Unit Tests with JUnit 5.

Author: MattCarneiiro