<div align="center">

# 🏥 HealthCare Java Chatbot

### *AI-Powered Medical Assistant — Disease Info · Symptom Checker · Appointment Booking*

[![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9.6-red?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Tomcat](https://img.shields.io/badge/Tomcat-9.0-yellow?style=for-the-badge&logo=apachetomcat&logoColor=white)](https://tomcat.apache.org/)
[![SQLite](https://img.shields.io/badge/SQLite-3.46-blue?style=for-the-badge&logo=sqlite&logoColor=white)](https://sqlite.org/)
[![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://docker.com/)
[![GitHub Actions](https://img.shields.io/badge/CI%2FCD-GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)](https://github.com/features/actions)

[![Build Status](https://github.com/shivuu773/HealthJavaChatBot/actions/workflows/deploy.yml/badge.svg)](https://github.com/shivuu773/HealthJavaChatBot/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/shivuu773/HealthJavaChatBot/pulls)

</div>

---

## 📖 Table of Contents

- [About the Project](#-about-the-project)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [Running Locally](#-running-locally)
- [Docker Deployment](#-docker-deployment)
- [CI/CD Pipeline](#-cicd-pipeline)
- [API Endpoints](#-api-endpoints)
- [Screenshots](#-screenshots)
- [Contributing](#-contributing)
- [Author](#-author)

---

## 🌟 About the Project

**HealthCare Java Chatbot** is a full-stack Java web application that acts as a 24/7 AI-powered medical assistant. It helps users understand diseases, check symptoms, get medicine guidance, and book doctor appointments — all from a single, modern web interface.

> ⚕️ *Disclaimer: For informational purposes only. Always consult a qualified doctor for medical advice.*

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| 🤖 **AI Chatbot** | Ask health questions in natural language and get instant, accurate responses |
| 🔍 **Symptom Checker** | Describe symptoms and match possible diseases with precautions |
| 🦠 **Disease Database** | Detailed info for 20+ diseases — causes, symptoms, medicines & prevention |
| 💊 **Medicine Guide** | OTC medication recommendations with dosage guidance |
| 📅 **Appointment Booking** | Schedule appointments with doctors across all major departments |
| 🚨 **Emergency Guide** | Critical emergency numbers (108, 112, 102) and warning signs |
| 🏥 **Department Search** | Find doctors by Cardiology, Neurology, Orthopedics & more |

### 🦠 Diseases Covered (20+)

`Common Cold` · `Influenza` · `COVID-19` · `Diabetes` · `Hypertension` · `Asthma` · `Migraine` · `Dengue` · `Malaria` · `Typhoid` · `Anxiety` · `Depression` · `Arthritis` · `Anemia` · `Obesity` · and more...

---

## 🛠 Tech Stack

### Backend
- **Java 17** — Core programming language
- **Jakarta EE / Servlet API 4.0** — Web servlet framework
- **JSP + JSTL** — Server-side templating
- **SQLite** via `sqlite-jdbc` — Lightweight embedded database
- **Google Gson 2.10** — JSON serialization/deserialization

### Build & Server
- **Apache Maven 3.9.6** — Build automation & dependency management
- **Apache Tomcat 9** — Servlet container
- **Tomcat7 Maven Plugin** — Embedded dev server

### Frontend
- **HTML5 + CSS3** — Responsive UI with glassmorphism design
- **Vanilla JavaScript** — Fetch API for async chatbot communication
- **Font Awesome 6** — Icons

### DevOps
- **Docker** — Containerization
- **GitHub Actions** — CI/CD pipeline
- **GitHub Container Registry (GHCR)** — Docker image hosting

---

## 📁 Project Structure

```
HealthJavaChatBot/
├── 📄 pom.xml                          # Maven configuration
├── 🐳 Dockerfile                       # Docker build file
├── 🚀 run.bat                          # Windows quick-start script
├── .github/
│   └── workflows/
│       └── deploy.yml                  # GitHub Actions CI/CD
└── src/main/
    ├── java/com/healthcare/
    │   ├── dao/
    │   │   ├── AppointmentDAO.java     # Appointment database operations
    │   │   └── DiseaseDAO.java        # Disease database operations
    │   ├── db/
    │   │   ├── DatabaseConnection.java # SQLite connection manager
    │   │   ├── DatabaseInitializer.java# DB schema + seed data
    │   │   └── PrintAppointments.java  # CLI utility for viewing records
    │   ├── model/
    │   │   ├── Appointment.java        # Appointment POJO
    │   │   └── Disease.java           # Disease POJO
    │   └── servlet/
    │       ├── IndexServlet.java       # Home page → /home
    │       ├── ChatbotServlet.java     # AI chatbot logic → /chat
    │       ├── DiseaseServlet.java     # Disease listing → /disease
    │       ├── AppointmentServlet.java # Appointments → /appointment
    │       └── SearchServlet.java      # Symptom search → /search
    └── webapp/
        ├── index.html                  # Landing redirect
        ├── index.jsp                   # Main home page
        ├── css/style.css              # Global styles
        └── WEB-INF/
            ├── web.xml                # Servlet configuration
            └── views/
                ├── index.jsp          # Home view
                ├── disease-list.jsp   # Disease list page
                ├── disease-detail.jsp # Disease detail page
                ├── appointment.jsp    # Appointment booking form
                ├── search.jsp        # Symptom search page
                ├── error.jsp         # 404 / 500 error page
                └── includes/
                    ├── header.jsp    # Shared navbar
                    └── footer.jsp    # Shared footer
```

---

## 🚀 Getting Started

### Prerequisites

| Requirement | Version | Download |
|-------------|---------|----------|
| Java JDK | 17+ | [Download](https://adoptium.net/) |
| Apache Maven | 3.6+ | [Download](https://maven.apache.org/download.cgi) |
| Git | Any | [Download](https://git-scm.com/) |

### Clone the Repository

```bash
git clone https://github.com/shivuu773/HealthJavaChatBot.git
cd HealthJavaChatBot
```

---

## 💻 Running Locally

### Option 1 — One-Click (Windows)

Double-click `run.bat` or run in PowerShell:

```powershell
.\run.bat
```

### Option 2 — Maven Command

```bash
mvn clean tomcat7:run
```

### Option 3 — PowerShell Script

```powershell
.\start.ps1
```

Once started, open your browser:

```
http://localhost:8080/healthcare
```

> 💡 **First run** downloads all Maven dependencies (~100MB). Subsequent runs are instant.

---

## 🐳 Docker Deployment

### Build & Run Locally

```bash
# Build the image
docker build -t healthcare-chatbot .

# Run the container
docker run -d -p 8080:8080 --name healthcare healthcare-chatbot

# Open in browser
# http://localhost:8080/healthcare
```

### Pull from GitHub Container Registry

```bash
docker pull ghcr.io/shivuu773/healthcare-chatbot:latest
docker run -d -p 8080:8080 ghcr.io/shivuu773/healthcare-chatbot:latest
```

---

## ⚙️ CI/CD Pipeline

This project uses **GitHub Actions** for automated building and deployment.

```
Push to main
     │
     ▼
┌─────────────────────┐
│  🔨 Build & Package  │  → Maven build + WAR artifact upload
│  (ubuntu-latest)    │
└──────────┬──────────┘
           │ on success
           ▼
┌─────────────────────┐
│  🐳 Docker Build     │  → Build & push to ghcr.io
│  & Push to GHCR     │
└─────────────────────┘
```

**Workflow file:** [`.github/workflows/deploy.yml`](.github/workflows/deploy.yml)

---

## 🌐 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/healthcare/home` | Home page |
| `POST` | `/healthcare/chat` | Chatbot — send message, get JSON response |
| `GET` | `/healthcare/disease` | List all diseases |
| `GET` | `/healthcare/disease?id={id}` | Disease detail page |
| `GET` | `/healthcare/search?q={query}` | Symptom / disease search |
| `GET` | `/healthcare/appointment` | Appointment booking form |
| `POST` | `/healthcare/appointment` | Submit appointment |

### Chatbot Request / Response

**Request:**
```http
POST /healthcare/chat
Content-Type: application/x-www-form-urlencoded

message=I have fever and cough
```

**Response:**
```json
{
  "response": "Based on your symptoms of **fever and cough**...\n\n• Possible: Common Cold, Influenza\n💊 Recommended: Paracetamol 500mg",
  "type": "symptom"
}
```

---

## 📸 Screenshots

### 🏠 Home Page — Hero Section
> AI-powered healthcare assistant with glassmorphism design, animated hero, and quick-access disease tags.

### 💬 Chatbot Interface
> Real-time chatbot with typing indicators, quick reply buttons, and formatted medical responses.

### 📅 Appointment Booking
> Full appointment form with doctor selection by department, date/time picker, and confirmation.

### 🦠 Disease Library
> Browse and search 20+ diseases with detailed symptom, medicine, and prevention information.

---

## 🤝 Contributing

Contributions are welcome! Here's how:

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/amazing-feature`
3. **Commit** your changes: `git commit -m 'Add amazing feature'`
4. **Push** to the branch: `git push origin feature/amazing-feature`
5. **Open** a Pull Request

Please make sure to update tests as appropriate.

---

## 📜 License

Distributed under the **MIT License**. See [`LICENSE`](LICENSE) for more information.

---

## 👨‍💻 Author

<div align="center">

**Shivam Yadav**

[![GitHub](https://img.shields.io/badge/GitHub-shivuu773-181717?style=for-the-badge&logo=github)](https://github.com/shivuu773)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Shivam_Yadav-0A66C2?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/shivuu773)

*Made with ❤️ for better healthcare accessibility*

⭐ **Star this repo** if you found it helpful!

</div>

---

<div align="center">
<sub>© 2026 Shivam Yadav · HealthCare Java Chatbot · MIT License</sub>
</div>
