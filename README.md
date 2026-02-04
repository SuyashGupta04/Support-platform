# 🛠️ Merchant Support Ticket & Debug Console Platform

A full-stack Support Engineering platform built with **Spring Boot + MongoDB + React + Tailwind** that simulates a real merchant support environment with ticket management, debugging tools, admin analytics, and role-based security.

Designed to demonstrate **technical support engineering skills**, backend API debugging, role-based access control, and production-style internal tooling.

---

## 🚀 Features

### 🎫 Ticket System
- Create / view / delete support tickets
- Status workflow: OPEN → IN_PROGRESS → RESOLVED
- Priority levels: HIGH / MEDIUM / LOW
- Ticket assignment to agents
- Comment threads per ticket
- Search + filter + pagination
- Ticket detail drawer view

---

### 🔐 Authentication & Security
- JWT authentication
- BCrypt password hashing
- Role-based access (ADMIN / SUPPORT)
- Protected routes (frontend + backend)
- Session expiry timer
- Auto logout on token expiry
- Role-aware UI navigation

---

### 🧪 Debug Console
- API test console for support engineers
- External API test endpoint
- Request timing + status logging
- API log storage via filter layer

---

### 👨‍💼 Admin Panel
- User management
- Role promotion (SUPPORT ↔ ADMIN)
- Admin-only endpoints
- Route guards

---

### 📊 Admin Analytics Dashboard
- Ticket status distribution
- Priority distribution charts
- Assigned vs unassigned counts
- Total users / total tickets metrics
- Chart visualizations (Recharts)

---

### 🎨 UI/UX
- React + Vite
- Tailwind CSS
- Dashboard layout
- Toast notifications
- Drawer panels
- Metrics cards
- Agent workload chart
- Role-aware navigation bar

---

## 🏗️ Tech Stack

### Backend
- Spring Boot
- Spring Security
- Spring Data MongoDB
- JWT (jjwt)
- Lombok
- Validation
- REST APIs

### Frontend
- React (Vite)
- Tailwind CSS
- Axios
- React Router
- Recharts

### Database
- MongoDB

---

## 📂 Project Structure

support-platform/
├── backend/
│ ├── controller
│ ├── service
│ ├── repository
│ ├── model
│ ├── config
│ └── dto
│
├── support-ui/
│ ├── pages
│ ├── components
│ ├── api
│ ├── hooks
│ └── utils


---

## ⚙️ Local Setup

### 1️⃣ Clone

git clone https://github.com/YOURNAME/support-platform.git


---

### 2️⃣ Start MongoDB

mongod


Database used:

support_platform_db


---

### 3️⃣ Run Backend

cd backend
mvn spring-boot:run


Backend runs on:

http://localhost:8080


---

### 4️⃣ Run Frontend

cd support-ui
npm install
npm run dev


UI runs on:

http://localhost:5173


---

## 🔑 Default API Flow

### Register

POST /api/auth/register


### Login

POST /api/auth/login


Returns JWT → used as:

Authorization: Bearer <token>


---

## 📡 Core API Endpoints

POST /api/tickets
GET /api/tickets
PUT /api/tickets/{id}/status
PUT /api/tickets/{id}/assign
DELETE /api/tickets/{id}

POST /api/tickets/{id}/comments
GET /api/tickets/{id}/comments

GET /api/admin/users
PUT /api/admin/users/{id}/role

GET /api/admin/stats
GET /api/debug/*


---

## 🎯 Resume Value

This project demonstrates:

- Support platform design
- API debugging workflows
- Role-based security implementation
- JWT session management
- Backend log instrumentation
- Admin analytics tooling
- Full-stack integration
- Production-style internal dashboards

---

## 🎤 Interview Talking Points

- JWT role claims + route guards
- BCrypt password security
- Ticket workflow modeling
- Debug console for support teams
- Analytics aggregation queries
- Session expiry handling
- Admin access controls

---

## 📌 Future Enhancements

- Email notifications
- SLA tracking
- WebSocket live updates
- Audit log explorer
- Multi-tenant merchant support



