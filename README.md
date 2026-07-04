# AI Competitor Analysis Tool

A full-stack AI-powered competitor analysis platform built using Spring Boot, MySQL, React, and Gemini AI.

This tool compares two competing apps or companies, identifies product gaps, analyzes UX, business model, target users, and generates strategic product recommendations using AI.

## Project Overview

Example input:

App One: Swiggy  
App Two: Zomato  
Industry: Food Delivery

The system generates:

- Core feature comparison
- UX and design comparison
- Target user analysis
- Business model comparison
- Product gaps
- Market opportunity
- Strategic recommendation
- Winner analysis

## Tech Stack

Backend:

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Gemini AI API
- Validation
- REST API

Frontend:

- React
- Vite
- Axios
- Bootstrap
- Custom CSS

Tools:

- IntelliJ IDEA
- MySQL Workbench
- Postman
- Git and GitHub

## Features

- Generate AI-powered competitor analysis
- Save reports in MySQL database
- View all generated reports
- View single report by ID
- Search reports by app name or industry
- View latest reports
- Delete reports
- Input validation
- Global exception handling
- Modern responsive React UI
- CORS configured for frontend-backend communication

## Project Structure

competitoranalysistool

- src/main/java/com/kumaran/competitoranalysistool
  - config
  - controller
  - dto
  - entity
  - exception
  - repository
  - service

- src/main/resources
  - application.properties

- frontend
  - src
    - App.jsx
    - App.css
    - main.jsx
  - package.json

- pom.xml
- README.md

## Database

Database name:

competitor_analysis_db

Create database:

CREATE DATABASE competitor_analysis_db;

Main tables:

- competitor_analysis
- analysis_report

## Backend Setup

Clone the repository:

git clone https://github.com/kumarandsm3-lgtm/competitor-analysis-tool.git

Open the root folder in IntelliJ IDEA:

competitoranalysistool

Configure MySQL in:

src/main/resources/application.properties

Example configuration:

spring.application.name=competitoranalysistool  
server.port=8080  
spring.datasource.url=jdbc:mysql://localhost:3306/competitor_analysis_db  
spring.datasource.username=root  
spring.datasource.password=1234  
spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true  
spring.jpa.properties.hibernate.format_sql=true  
gemini.api.key=${GEMINI_API_KEY}  
gemini.model=gemini-2.5-flash

Important: Do not commit your actual Gemini API key to GitHub.

Correct:

gemini.api.key=${GEMINI_API_KEY}

Wrong:

gemini.api.key=your_actual_api_key

## Gemini API Key Setup

Set the Gemini API key as an environment variable in IntelliJ IDEA.

Steps:

Run → Edit Configurations → Environment variables

Add:

Name: GEMINI_API_KEY  
Value: your_gemini_api_key

The API key should not be written directly inside application.properties.

## Run Backend

Run the main class:

CompetitoranalysistoolApplication.java

Backend runs at:

http://localhost:8080

Test API:

GET http://localhost:8080/test

Expected response:

Backend is working

## Frontend Setup

Go to frontend folder:

cd frontend

Install dependencies:

npm install

Run frontend:

npm run dev

Frontend runs at:

http://localhost:5173

Sometimes Vite may run at:

http://localhost:5174

## API Endpoints

Test API:

GET /test

Generate AI Competitor Analysis:

POST /api/analysis/generate

Request body:

{
  "appOne": "Swiggy",
  "appTwo": "Zomato",
  "industry": "Food Delivery"
}

Get all reports:

GET /api/analysis

Get report by ID:

GET /api/analysis/{id}

Search reports:

GET /api/analysis/search?keyword=Swiggy

Get latest reports:

GET /api/analysis/latest

Delete report:

DELETE /api/analysis/{id}

## Sample Output

{
  "reportId": 1,
  "appOne": "Swiggy",
  "appTwo": "Zomato",
  "industry": "Food Delivery",
  "winner": "Zomato",
  "coreFeatures": "AI generated competitor feature comparison",
  "uxDesign": "AI generated UX comparison",
  "targetUser": "AI generated target user analysis",
  "businessModel": "AI generated business model comparison",
  "gaps": "AI identified product gaps",
  "opportunity": "AI generated product opportunity",
  "strategicRecommendation": "AI generated strategic recommendation"
}

## Frontend Screens

The React frontend includes:

- AI competitor analysis form
- Modern SaaS-style dashboard UI
- Report result cards
- Saved reports table
- Search reports
- Latest reports
- Delete report action

## What This Project Demonstrates

- Spring Boot REST API development
- MySQL database integration
- Gemini AI API integration
- AI prompt engineering
- DTO usage
- Validation
- Exception handling
- CORS configuration
- React frontend development
- Axios API communication
- Full-stack project architecture

## Resume Line

Built a full-stack AI-powered Competitor Analysis Tool using Spring Boot, MySQL, React, and Gemini AI to compare competing apps, identify product gaps, generate strategic recommendations, store reports, and provide search/latest/delete features through a modern dashboard.

## Future Improvements

- User login and authentication
- JWT security
- PDF report export
- Dashboard charts
- Competitor history tracking
- Email report sharing
- Cloud deployment
- Docker support

## Author

Kumaran K

GitHub: https://github.com/kumarandsm3-lgtm