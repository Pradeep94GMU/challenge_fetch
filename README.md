# 📄 Receipt Processor Application

## 📚 Table of Contents
- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Setup Instructions](#setup-instructions)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Running the Application](#running-the-application)
  - [Running with Maven](#running-with-maven)
  - [Running with Docker](#running-with-docker)
- [API Documentation](#api-documentation)
  - [Endpoints](#endpoints)
  - [Example Requests](#example-requests)
- [Testing the Application](#testing-the-application)
- [Project Structure](#project-structure)
- [Error Handling](#error-handling)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

## Project Overview
The **Receipt Processor** application is a microservice built with Spring Boot. It calculates points based on receipt details provided by users. Points are determined by:
- The length of the retailer's name
- Specific characteristics of the total amount
- Details of the purchased items
- The date and time of purchase

The application is designed to store data **in-memory** during its runtime, which means data will not persist after the application stops.

## Technologies Used
- **Java**: Version 17
- **Spring Boot**: Version 3.x
- **Maven**: Version 3.8+
- **Docker**: For containerization

## Features
- 🏷️ **Retailer Name Points**: Points awarded based on alphanumeric characters in the retailer's name.
- 💵 **Total Amount Points**: Points for round dollar totals and multiples of 0.25.
- 📦 **Item Description Points**: Bonus points for every two items on the receipt.
- 🛒 **Item Price Points**: Bonus points based on item description length.
- 🗓️ **Purchase Date Points**: Bonus points for odd-numbered purchase dates.
- ⏰ **Purchase Time Points**: Bonus points for purchases made between 2 PM and 4 PM.

## Setup Instructions

### Prerequisites
Ensure you have the following installed:
- **Java 17**: 
- **Maven**: 
- **Docker**: - Docker should be installed on your machine.

### Installation

## Running the Application with Docker

To build and run the application using Docker, follow the steps below.

### Build the Docker Image

1. **Clone the repository** (if you haven't already):
   ```bash
   git clone https://github.com/Pradeep94GMU/challenge_fetch.git
   cd challenge_fetch 
2. **Inside your project directory (where your Dockerfile is located), run the following command to build the Docker image and run the container
   ```bash
    docker build -t receipt-processor .
    docker run -p 8080:8080 receipt-processor

## Project Structure

```bash
challenge_fetch/
├── src/
│   ├── main/java/com/pradeep/receipt_processor/
│   ├── controller/        # API controllers
│   ├── model/             # Data models
│   ├── service/           # Business logic
│   └── resources/
│       └── application.properties
├── Dockerfile
├── README.md
└── pom.xml
