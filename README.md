# 🥗 Calorie Counter — Food & Daily Nutrition Tracker

A simple, fast, and modern full-stack **Calorie Counter** web application designed to log daily food consumption, calculate total calories, and track nutritional consistency.

Built with **Java 24**, **Spring Boot**, **Spring Data JPA**, **MySQL**, and a responsive Single Page Interface.

---

## 🚀 Features

- **Daily Calorie Calculator**: Automatically calculates total daily calories, tracks remaining calories against your customizable target goal, and provides an animated progress bar.
- **Log Food Consumption**: Easily record food name, calories (kcal), meal category (Breakfast, Lunch, Dinner, Snack), and portion/serving size.
- **Meal Category Breakdown**: Instant subtotals for each meal group:
  - 🌅 Breakfast
  - ☀️ Lunch
  - 🌙 Dinner
  - 🍎 Snacks
- **Quick-Add Presets**: 1-click shortcut buttons to instantly log common food items (e.g., Apple, Boiled Egg, Chicken Rice, Salad).
- **Date Switcher**: Seamlessly review and log meals for any past or future date with Next/Prev controls, calendar picker, or "Today" shortcut.
- **7-Day Calorie Trend**: Interactive bar chart displaying your calorie intake history and goal adherence over the past 7 days.
- **Item Management**: Delete or edit logged food items in real-time with instant calorie recalculation.
- **Persistent Storage**: All entries are stored in a relational MySQL database via Spring Data JPA.

---

## 🛠️ Technology Stack

| Layer | Technology |
| :--- | :--- |
| **Language** | Java 24.0.2 |
| **Framework** | Spring Boot (Spring Web MVC) |
| **ORM / Data Access** | Spring Data JPA (Hibernate) |
| **Database** | MySQL 8.0 with `mysql-connector-j` |
| **Build Tool** | Apache Maven with Maven Wrapper (`mvnw`) |
| **Frontend** | Responsive Vanilla HTML5, CSS3, & Modern JavaScript |

---

## 📋 Prerequisites

Before running the project, make sure you have:

1. **Java Development Kit (JDK) 24** installed:
   ```bash
   java -version
   ```
2. **MySQL Server** installed and running on port `3306`:
   ```bash
   netstat -ano | findstr :3306
   ```

---

## ⚙️ Configuration & Database Setup

The database connection is configured in [`src/main/resources/application.properties`](demo/src/main/resources/application.properties):

```properties
spring.application.name=calorie-counter

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/calorie_tracker?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=402338
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate Auto-DDL
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Server Port
server.port=8080
```

> **Note**: Update `spring.datasource.password` if your local MySQL root password is different. The database `calorie_tracker` and the table `food_logs` are created automatically on startup.

---

## 🏃 How to Run the Application

### 1. Open Terminal and Navigate to Project Directory
```powershell
cd c:\ip\demo
```

### 2. Start with Maven Wrapper
- **On Windows (PowerShell / Command Prompt):**
  ```powershell
  .\mvnw.cmd spring-boot:run
  ```
- **On macOS / Linux:**
  ```bash
  ./mvnw spring-boot:run
  ```

### 3. Open the Web Application
Once you see the log message:
```text
Tomcat started on port 8080 (http) with context path '/'
Started DemoApplication in ... seconds
```
Open your browser and navigate to:
👉 **[http://localhost:8080](http://localhost:8080)**

---

## 📡 REST API Documentation

The backend provides clean RESTful JSON endpoints:

### 1. Get Daily Summary & Food Items
- **Endpoint**: `GET /api/meals`
- **Query Parameters**:
  - `date` *(optional)*: Date in `YYYY-MM-DD` format (defaults to current day).
  - `target` *(optional)*: Target calorie goal (default `2000`).
- **Sample Response**:
  ```json
  {
    "date": "2026-10-21",
    "totalCalories": 870,
    "breakfastCalories": 320,
    "lunchCalories": 550,
    "dinnerCalories": 0,
    "snackCalories": 0,
    "targetCalories": 2000,
    "remainingCalories": 1130,
    "logs": [
      {
        "id": 1,
        "foodName": "Oatmeal with Almonds",
        "calories": 320,
        "mealType": "BREAKFAST",
        "servingSize": "1 bowl",
        "logDate": "2026-10-21",
        "createdAt": "2026-10-21T08:30:00"
      }
    ]
  }
  ```

### 2. Log Food Consumption
- **Endpoint**: `POST /api/meals`
- **Headers**: `Content-Type: application/json`
- **Body**:
  ```json
  {
    "foodName": "Grilled Chicken Salad",
    "calories": 350,
    "mealType": "LUNCH",
    "servingSize": "1 plate",
    "logDate": "2026-10-21"
  }
  ```
- **Response**: `201 Created` with saved food object.

### 3. Update a Food Entry
- **Endpoint**: `PUT /api/meals/{id}`
- **Body**: Updated JSON object.
- **Response**: `200 OK`.

### 4. Delete a Food Entry
- **Endpoint**: `DELETE /api/meals/{id}`
- **Response**: `204 No Content`.

### 5. Get 7-Day Calorie Trend
- **Endpoint**: `GET /api/meals/trend?days=7&target=2000`
- **Response**: Array of daily totals for chart rendering.

---

## 📂 Project Directory Structure

```text
c:\ip\demo\
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── DemoApplication.java          # Spring Boot main entry point
│   │   │   ├── controller/
│   │   │   │   └── FoodLogController.java    # REST API endpoints
│   │   │   ├── dto/
│   │   │   │   └── DailySummaryDTO.java      # Transfer object for daily statistics
│   │   │   ├── model/
│   │   │   │   └── FoodLog.java              # JPA entity (table: food_logs)
│   │   │   ├── repository/
│   │   │   │   └── FoodLogRepository.java    # Spring Data JPA queries
│   │   │   └── service/
│   │   │       └── FoodLogService.java       # Calorie math & business logic
│   │   └── resources/
│   │       ├── application.properties        # MySQL and server configuration
│   │       └── static/
│   │           └── index.html                # Responsive web user interface
│   └── test/
├── pom.xml                                   # Maven dependencies & Java 24 configuration
├── PROJECT_EXPLANATION.txt                   # In-depth file-by-file breakdown
└── README.md                                 # Project documentation
```

---

## 🛑 How to Stop the App

To shut down the web server:
- Press `Ctrl + C` in the terminal where Spring Boot is running.
- Press `Y` if prompted to terminate batch job.
