# AI Competitor Analysis Tool

AI Competitor Analysis Tool is a Spring Boot backend project that compares two competing apps and generates a structured competitor analysis report.

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- REST API
- Postman
- Gemini AI API - upcoming

## Features

- Generate competitor analysis report
- Compare two apps such as Swiggy vs Zomato
- Save analysis details in MySQL
- View all saved reports
- View report by ID

## API Endpoints

### Test API

GET http://localhost:8080/test

### Generate Analysis

POST http://localhost:8080/api/analysis/generate

Request body:

```json
{
  "appOne": "Swiggy",
  "appTwo": "Zomato",
  "industry": "Food Delivery"
}
