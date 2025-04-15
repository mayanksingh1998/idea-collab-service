# 💡 Idea Collaboration Service

A Spring Boot RESTful service for managing employee ideas, collaborations, and reactions in an organization.

## 📦 Modules Overview

- **Authentication**: Login and onboarding of employees
- **Employee**: Manage and fetch employee details
- **Idea**: Create, post, react, and retrieve ideas
- **Collaboration**: Request and manage idea collaborations

---

## 🛡️ Authentication

### 🔐 Login

**POST** `/auth/employee/login`

```json
Request:
{
  "email": "jane@finbox.com",
  "password": "securePass123"
}
```

**Response:** Returns a JWT token with employee info.

---

## 👤 Employee APIs

### 🧾 Get Logged-in Employee

**GET** `/employee`

**Headers:** `employeeId: <id>` (set internally from token/request)

---

### ✍️ Onboard New Employee

**POST** `/skip-auth/employee`

```json
Request:
{
"name": "Mayank singh h",
"email": "mayank3@gmail.com",
"role": "DEVELOPER",
"department": "ENGINEERING",
"location": "New York",
"managerId": "EMP12345",
"password": "mayank123"
}

```

---

### 💡 Get Ideas Created by an Employee

**GET** `/employee/ideas`

**Header:**
```http
employeeId: emp123
```

---

### 🤝 Get Collaboration Requests for Employee

**GET** `/employee/idea/collaborations`

---

## 💡 Idea APIs

### ➕ Create a New Idea

**POST** `/idea`

```json
Request:
{
  "title": "New AI Tool",
  "description": "An idea for internal AI-powered documentation",
  "tagIds": ["tag123", "tag456"]
}
```

---

### 👍 React to an Idea

**PUT** `/idea/{ideaId}/react`

```json
Request:
{
  "reactionType": "UPVOTE"
}
```

---

### 📤 Post an Idea

**PUT** `/idea/{ideaId}/post`

---

### 🔍 Get Idea by ID

**GET** `/idea/{ideaId}`

---

### 📊 Get Reactions on Idea

**GET** `/idea/{ideaId}/reactions`

---

### 🧃 Filter Ideas

**POST** `/idea/filter`

```json
Request:
{
  "employeeIds": ["emp123", "emp456"],
  "statuses": ["APPROVED", "OPEN"],
  "tags": ["AI", "Tech"],
  "startDate": "2024-12-01T00:00:00",
  "endDate": "2025-04-10T23:59:59",
  "sortBy": "votesCount",
  "order": "desc"
}
```

---

## 🤝 Collaboration APIs

### 📨 Raise a Collaboration Request

**POST** `/idea/collaborate`

```json
Request:
{
  "ideaId": "idea123",
  "message": "Would love to work on this!"
}
```

---

### ✅ Respond to a Collaboration Request

**PUT** `/idea/collaborate/{collaborateId}`

```json
Request:
{
  "action": "ACCEPT"
}
```

---

### 📥 Get Collaborations for an Idea

**GET** `/idea/{ideaId}/collaborations`

---

## 🛠️ Dev Commands

Use the `Makefile` for local development:

```bash
make up      # Start server
make test    # Run tests
make down    # Stop containers
make logs    # View logs
```

---

## 🔐 Security

- Authenticated endpoints require a JWT token or an internal `employeeId` header (mocked in request for now).
- Spring Security can be added for production-grade auth.

---

## 🧪 Testing

Unit tests are under `src/test/java`. Run with:

```bash
make test
```

---

## 🧱 Tech Stack

- Spring Boot
- PostgreSQL
- Redis
- Liquibase
- Docker & Docker Compose
