# 📄 Receipt Processor Application

## 📚 Table of Contents
- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Setup Instructions](#setup-instructions)
  - [Prerequisites](#prerequisites)
- [Running the Application](#running-the-application)
  - [Running with Maven](#running-with-maven)
  - [Running with Docker](#running-with-docker)

  - [Endpoints](#endpoints)
  - [Example Requests](#example-requests)
- [Testing the Application](#testing-the-application)
- [Project Structure](#project-structure)


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

### Steps Instruction

Clone the repository:
     
    
    git clone https://github.com/Pradeep94GMU/challenge_fetch.git
    cd challenge_fetch

## Running the Application with Maven
    
    mvn clean package


once you done with maven clean package, you will get another folder which is target with .jar file. like the below:


![Jar File](assets/pci1.jpeg)
## Running the Application with Docker
To build and run the application using Docker, follow the steps below.

Steps

  Build the Docker image:
  
    
    docker build -t receipt-processor .
  Run the Docker container:
  
    
    docker run -p 8080:8080 receipt-processor
  Access the application at http://localhost:8080.

## example-requests
1. Process Receipt
Endpoint: /api/v1/receipts/process
Method: POST
Description: Processes receipt and returns calculated points.
Example Request:
    
        {
          "retailer": "Target",
          "purchaseDate": "2022-01-01",
          "purchaseTime": "13:01",
          "items": [
            {
              "shortDescription": "Mountain Dew 12PK",
              "price": "6.49"
            },{
              "shortDescription": "Emils Cheese Pizza",
              "price": "12.25"
            },{
              "shortDescription": "Knorr Creamy Chicken",
              "price": "1.26"
            },{
              "shortDescription": "Doritos Nacho Cheese",
              "price": "3.35"
            },{
              "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
              "price": "12.00"
            }
          ],
          "total": "35.35"
        }

## Example Response:

    
    {"id":"411f0efa-20fb-4a3d-a7da-1c00fdc06871"}

### 2. Retrieve Points
Endpoint: /api/v1/receipts/{id}/points
Method: GET
Example Response:

    
    {
      "points": 28
    }
Also, You can see the detail explaination in the console. like the below:
    
    
            Starting Points Calculation...
            Retailer Name Points: 6
            Round Dollar Total: 0 (not round)
            Multiple of 0.25: 0 (not a multiple of 0.25)
            Total Points from Amount: 0
            Items Points: 5 (2 items give 5 points)
            Item: "Cereal" Price: 2.99 - Points: 1 (description length multiple of 3)
            Item: "Milk" Price: 3.49 - Points: 0 (description length not multiple of 3)
            Item Price Points: 1
            Purchase Date Points: 6 (2023-11-01 is an odd day)
            Purchase Time Points: 10 (time is between 2 PM and 4 PM)
            Total Points: 28




## Project Structure


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
