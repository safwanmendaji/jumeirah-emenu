# Jumeirah E-Menu Microservices

This project consists of modular microservices to support a digital E-Menu system, including menu management, restaurant information handling, and QR-based ordering.

---

## 🚧 Architecture Overview

| Service Name       | Description                                      | Port |
| ------------------ | ------------------------------------------------ | ---- |
| restaurant-service | Manages restaurant details and info              | 9091 |
| menu-service       | Manages menu, sections, categories, items, etc.  | 9092 |
| qrservice          | Handles QR-based ordering and restaurant lookups | 9093 |

All services are registered with **Eureka Discovery Server** for service discovery.

---

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot 3.3.1**
- **Spring Cloud 2023.0.2**
- **MongoDB**
- **RestTemplate** for inter-service communication (can be upgraded to OpenFeign)
- **Eureka** for service registration

---

## 📂 How to Run

### Prerequisites

- JDK 17
- Maven
- MongoDB (running on default port)
- Eureka Discovery Server

### Steps

1. **Start Eureka Discovery Server** first. (service-registery)
2. **Start API Gateway** if present. (api-registery)
3. Open `application.properties` of both `restaurant-service` and `menu-service` and update MongoDB URI to point to live MongoDB connection.


Ensure that Eureka server is running before starting microservices.

---

## 📅 API Endpoints

### 🏨 Restaurant Service (`http://localhost:9091/api/restaurant`)

| Method | Endpoint                    | Description            |
| ------ | --------------------------- | ---------------------- |
| GET    | `/getInfo/{restaurantCode}` | Get restaurant by code |

### 📋 Menu Service (`http://localhost:9092/api/menu`)

| Method | Endpoint                         | Description                       |
| ------ | -------------------------------- | --------------------------------- |
| GET    | `/byrestaurantid/{restaurantId}` | Get menu with full nested details |
| GET    | `/getAll`                        | Get all menus                     |
| POST   | `/create`                        | Create new menu                   |
| PUT    | `/update/{menuId}`               | Update menu                       |
| DELETE | `/delete/{menuId}`               | Delete menu                       |

### 📱 QR Service (`http://localhost:9093/api/qr`)

| Method | Endpoint            | Description                         |
| ------ | ------------------- | ----------------------------------- |
| GET    | `/restaurant-menus` | Get combined restaurant + menu info |

```http
GET /api/qr/restaurant-menus?restaurantCode=AQPDR&tableNumber=101
```

---