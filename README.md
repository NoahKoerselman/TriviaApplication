#  Trivia Quiz Application

A trivia quiz application built with a Java Spring Boot backend and a React frontend.  
The application uses the Open Trivia Database as an external API.

---

## Project Goal

Users must be able to answer trivia questions without being able to see the correct answers in the raw API response.

To solve this, a backend API was created that acts as a middleware between the frontend and the Open Trivia Database.

---

##  Features

- Interactive trivia quiz UI  
- Secure backend (prevents exposing correct answers)  
- Configurable quiz settings:
  - Number of questions  
  - Category  
  - Difficulty  
- Score calculation on backend  
- REST API communication 

---

##  Build & Run Instructions

Follow these steps to run the project locally.

---

##  Step 1 — Prerequisites

Make sure you have installed:
- Java
- Node.js
- Maven
- Git

---

## Step 2 — Clone the repository

---

## Step 3 — Start the Backend (Spring Boot)

Navigate to the backend folder:
- cd backend

Start the backend:
- mvnw spring-boot:run

The backend runs on:
- http://localhost:8080

## Step 4 — Start the Frontend (React)

Open a new terminal and go to the frontend folder:
- cd frontend

Install dependencies:
- npm install

Start the frontend:
- npm run dev

The frontend runs on:
- http://localhost:5173

## Step 5 — Use the Application
- Open the frontend in your browser
- Select quiz settings (amount, category, difficulty)
- Click Start Quiz
- Answer the questions
- Submit answers to see your score
