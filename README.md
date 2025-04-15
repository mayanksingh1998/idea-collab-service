# Idea Collaboration Service

This is a Spring Boot-based backend application for managing and collaborating on ideas within an organization. It supports features like user authentication, idea creation, collaboration, tagging, reactions, and more.

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Docker & Docker Compose

### Using Makefile

#### Build and Run the Project
```bash
make up
```

#### Stop the Services
```bash
make down
```

#### Run Tests
```bash
make test
```

### Access Swagger UI
Once running, go to:
```
http://localhost:8080/swagger-ui/index.html
```

---

## 📦 API Endpoints Overview

### 🔐 AuthController
#### `POST /api/v1/auth/login`
- **Request:**
```json
{
  "email": "user@example.com",
  "password": "secret"
}
```
- **Response:**
```json
{
  "token": "jwt-token-here",
  "employeeId": 1,
  "name": "John Doe"
}
```

---

### 👤 EmployeeController
#### `POST /api/v1/employee`
- **Request:**
```json
{
  "email": "jane@company.com",
  "name": "Jane Smith"
}
```
- **Response:**
```json
{
  "message": "Employee created successfully",
  "employeeId": 2
}
```

#### `GET /api/v1/employee/{employeeId}/ideas`
- **Response:**
```json
[
  {
    "id": 1,
    "title": "Improve onboarding",
    "description": "Streamline new hire process",
    "status": "OPEN"
  }
]
```

---

### 💡 IdeaController
#### `POST /api/v1/idea`
- **Request:**
```json
{
  "title": "New Feature X",
  "description": "Details about feature X",
  "creatorId": 1,
  "tagIds": [1, 2]
}
```
- **Response:**
```json
{
  "id": 10,
  "message": "Idea created successfully"
}
```

#### `GET /api/v1/idea/{id}`
- **Response:**
```json
{
  "id": 10,
  "title": "New Feature X",
  "description": "Details about feature X",
  "status": "ACTIVE",
  "tags": ["Innovation", "Tech"]
}
```

#### `POST /api/v1/idea/filter`
- **Request:**
```json
{
  "status": "OPEN",
  "tagIds": [1, 2]
}
```
- **Response:**
```json
[
  {
    "id": 10,
    "title": "New Feature X",
    "status": "ACTIVE"
  }
]
```

#### `POST /api/v1/idea/react`
- **Request:**
```json
{
  "ideaId": 10,
  "employeeId": 1,
  "action": "UP_VOTE"
}
```
- **Response:**
```json
{
  "message": "Reaction saved"
}
```

---

### 🤝 IdeaCollaborationController
#### `POST /api/v1/idea/collaboration/request`
- **Request:**
```json
{
  "ideaId": 10,
  "description": "wwvkuvwku",
}
```
- **Response:**
```json
{
  "message": "Collaboration request sent"
}
```

#### `POST /api/v1/idea/collaboration/action`
- **Request:**
```json
{
  "collaborationRequestId": 5,
  "action": "ACCEPT"
}
```
- **Response:**
```json
{
  "message": "Collaboration request accepted"
}
```

---

### 🏷️ TagController
#### `POST /api/v1/tag`
- **Request:**
```json
{
  "name": "Innovation"
}
```
- **Response:**
```json
{
  "id": 1,
  "message": "Tag created"
}
```

#### `GET /api/v1/tag`
- **Response:**
```json
[
  { "id": 1, "name": "Innovation" },
  { "id": 2, "name": "Efficiency" }
]
```

---

## 🧩 Entities & Relationships

**Employee** 1---* **Idea**

**Idea** *---* **Tag** (ManyToMany)

**Idea** 1---* **IdeaReaction**

**Idea** 1---* **IdeaCollaborationRequest**

**Employee** 1---* **IdeaReaction**

**Employee** 1---* **IdeaCollaborationRequest**

---

## 🛠 Technologies Used
- Java 17
- Spring Boot
- PostgreSQL
- Redis (for caching or token storage)
- Liquibase (DB migrations)
- Docker Compose
- Swagger (OpenAPI)

---
