# 🏦 The Java Bank

A **Spring Boot based Banking Backend Application** that provides secure REST APIs for user authentication, account management, and transactions.  
This project demonstrates backend development using **Java, Spring Boot, and Spring Security** with clean architecture and scalable design.

---

## 🚀 Tech Stack

- Java
- Spring Boot
- Spring Security
- REST APIs
- Maven
- H2 / MySQL Database
- JWT Authentication

---

## ✨ Features

- User Registration
- Secure Login Authentication
- JWT Token-based Security
- Bank Account Management
- Deposit and Withdrawal Operations
- Transaction History
- RESTful API Architecture
- Global Exception Handling
- Layered Architecture (Controller → Service → Repository)

---

## 📂 Project Structure

```
the-java-bank
│
├── src/main/java
│   ├── controller        # REST Controllers
│   ├── service           # Business Logic
│   ├── repository        # Database Access Layer
│   ├── entity            # Database Entities
│   ├── config            # Security & App Configurations
│   └── TheJavaBankApplication.java
│
├── src/main/resources
│   ├── application.properties
│
├── pom.xml
└── README.md
```

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository

```bash
git clone [https://github.com/YOUR_USERNAME/the-java-bank.git](https://github.com/sanketkr248/javaBankApp.git)
cd the-java-bank
```

### 2️⃣ Build the Project

```bash
mvn clean install
```

### 3️⃣ Run the Application

```bash
mvn spring-boot:run
```

or

```bash
java -jar target/the-java-bank.jar
```

---

## 🌐 Access the Application

```
http://localhost:8080
```

---

## 🔐 Example API Endpoints

### Register User

```
POST /api/auth/register
```

### Login

```
POST /api/auth/login
```

### Create Account

```
POST /api/account/create
```

### Get Account Details

```
GET /api/account/{id}
```

### Deposit Money

```
POST /api/account/deposit
```

### Withdraw Money

```
POST /api/account/withdraw
```

---

## 🧪 Testing APIs

You can test APIs using:

- Postman
- Swagger (if enabled)
- Curl

Example:

```bash
curl -X GET http://localhost:8080/api/account/1
```

---

## 📊 Architecture

```
Controller Layer
      ↓
Service Layer
      ↓
Repository Layer
      ↓
Database
```

This layered architecture improves **maintainability, scalability, and testing**.

---

## 🛡 Security

The application uses:

- Spring Security
- JWT Authentication
- Password Encryption
- Secure REST endpoints

---

## 🧑‍💻 Author

**Sanket Kumar**

Backend Software Engineer  
Java | Spring Boot | Microservices | Kafka | Docker

LinkedIn: [https://linkedin.com  ](https://www.linkedin.com/in/withsanket/)
GitHub: [ https://github.com](https://github.com/sanketkr248)

---

## ⭐ Contribution

Contributions are welcome!

1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Open a Pull Request

---

## 📜 License

This project is licensed under the **MIT License**.

---

## 🙌 Support

If you found this project useful, please give it a **⭐ star on GitHub**.
