# 💳 Mini Payment Gateway API

A secure, production-ready backend RESTful API built with **Java and Spring Boot** that simulates an online payment gateway system. This project is designed as a backend portfolio project for internship applications.

---

## 🚀 Features

- **Process Payments** — Securely verifies card details, checks available balance, and processes fund deductions.
- **Transaction History (Audit Log)** — Automatically logs every successful or failed payment attempt with timestamps and status messages.
- **Card Inquiry API** — Allows looking up card details and current balance via card number.
- **Input Validation** — Uses Bean Validation to block empty, null, or invalid data automatically.
- **Role-Based Access Control (RBAC)** — Secured with Spring Security, with separate `ADMIN` and `MERCHANT` roles.
- **Database Integration** — Persistent storage using MySQL and Spring Data JPA / Hibernate.

---

## 🛠️ Tech Stack

- **Language:** Java (JDK 17+)
- **Framework:** Spring Boot
- **Data Persistence:** Spring Data JPA, Hibernate, MySQL
- **Security:** Spring Security (HTTP Basic Auth + RBAC)
- **Validation:** Spring Boot Validation
- **Utilities:** Lombok
- **API Testing Tool:** Postman

---

## ⚙️ Project Setup & Configuration

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/mini-payment-gateway.git
```

### 2. Configure Database

Create a MySQL database named `payment_db`:

```sql
CREATE DATABASE payment_db;
```

Update `src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/payment_db?useSSL=false
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

### 3. Run the Application

Run the `PaymentGatewayApplication.java` file from your IDE (IntelliJ IDEA).

---

## 🌐 Security Roles

This application uses in-memory authentication with two configured roles:

| Role | Default Credentials | Access |
|------|--------------------|--------|
| **Admin** | `bank_admin` / `admin123` | Add cards, view transactions, look up cards, process payments |
| **Merchant** | `merchant_user` / `merchant123` | Process payments only |


---

## 📋 API Endpoints & Testing

### 1. Add a New Card — Admin Only

**URL:** `POST http://localhost:8080/api/payment/add-card`
**Headers:** `Authorization: Basic (bank_admin / admin123)`

**Body (JSON):**

```json
{
    "cardNumber": "1234-5678-9012-3456",
    "cardHolderName": "Ahmed Ali",
    "expiryDate": "12/28",
    "cvv": "123",
    "balance": 5000.0
}
```

### 2. Process a Payment — Admin or Merchant

**URL:** `POST http://localhost:8080/api/payment/pay`
**Headers:** `Authorization: Basic (merchant_user / merchant123)`

**Body (JSON):**

```json
{
    "cardNumber": "1234-5678-9012-3456",
    "amount": 500.0,
    "merchantId": "MERCHANT_01"
}
```

### 3. View Transaction History — Admin Only

**URL:** `GET http://localhost:8080/api/payment/transactions`

**Headers:** `Authorization: Basic (bank_admin / admin123)`

### 4. Look Up Card Details — Admin Only

**URL:** `GET http://localhost:8080/api/payment/card/1234-5678-9012-3456`

**Headers:** `Authorization: Basic (bank_admin / admin123)`