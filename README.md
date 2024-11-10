📄 Receipt Processor Application
📚 Table of Contents
Project Overview
Technologies Used
Features
Setup Instructions
Prerequisites
Installation
Running the Application
Running with Maven
Running with Docker
API Documentation
Endpoints
Example Requests
Testing the Application
Project Structure
Error Handling
Future Enhancements
Contributing
License
Project Overview
The Receipt Processor application is a microservice built with Spring Boot. It calculates points based on receipt details provided by users. Points are determined by:

The length of the retailer's name
Specific characteristics of the total amount
Details of the purchased items
The date and time of purchase
The application is designed to store data in-memory during its runtime, which means data will not persist after the application stops. The service is evaluated based on its ability to provide correct results and easy setup using Docker.

Technologies Used
Java: Version 17
Spring Boot: Version 3.x
Maven: Version 3.8+
Docker: For containerization
Features
🏷️ Retailer Name Points: Points are awarded based on the count of alphanumeric characters in the retailer's name.
💵 Total Amount Points: Points for round dollar totals and multiples of 0.25.
📦 Item Description Points: Bonus points for every two items on the receipt.
🛒 Item Price Points: Bonus points based on item description length.
🗓️ Purchase Date Points: Bonus points for odd-numbered purchase dates.
⏰ Purchase Time Points: Bonus points for purchases made between 2 PM and 4 PM.
Setup Instructions
Prerequisites
Ensure you have the following installed:

Java 17: Verify with java -version
Maven: Verify with mvn -version
Docker: Verify with docker --version
Installation
Clone the repository:
bash
Copy code
git clone https://github.com/Pradeep94GMU/challenge_fetch.git
cd challenge_fetch
Build the project:
bash
Copy code
./mvnw clean install
(Optional): Run unit tests to verify the build:
bash
Copy code
./mvnw test
Running the Application
Running with Maven
Start the Spring Boot application using Maven:
bash
Copy code
./mvnw spring-boot:run
The application will be accessible at http://localhost:8080.
Running with Docker
Build the Docker image:
bash
Copy code
docker build -t receipt-processor-app .
Run the Docker container:
bash
Copy code
docker run -p 8080:8080 receipt-processor-app
Visit http://localhost:8080 in your browser or use Postman to interact with the API.
API Documentation
Endpoints
1. Process Receipt
Endpoint: /api/v1/receipts/process
Method: POST
Description: Processes a receipt and calculates points based on receipt details.
Request Body:
json
Copy code
{
  "retailer": "Walmart",
  "total": "25.50",
  "items": [
    {"shortDescription": "Bread", "price": "2.50"},
    {"shortDescription": "Milk", "price": "3.50"}
  ],
  "purchaseDate": "2024-11-10",
  "purchaseTime": "15:30"
}
Response:
json
Copy code
{
  "id": "receipt-123",
  "points": 36
}
2. Retrieve Points
Endpoint: /api/v1/receipts/{id}/points
Method: GET
Description: Retrieves the points for a specific receipt using its ID.
Response:
json
Copy code
{
  "points": 36
}
Example Requests
Using Curl:

bash
Copy code
curl -X POST http://localhost:8080/api/v1/receipts/process \
-H "Content-Type: application/json" \
-d '{"retailer": "Walmart", "total": "25.50", "items": [{"shortDescription": "Bread", "price": "2.50"}, {"shortDescription": "Milk", "price": "3.50"}], "purchaseDate": "2024-11-10", "purchaseTime": "15:30"}'
Using Postman:

Create a new POST request.
Set the URL to http://localhost:8080/api/v1/receipts/process.
Use raw JSON in the Body section with the above payload.
Click Send to get a response.
Testing the Application
Run all tests using Maven:
bash
Copy code
./mvnw test
Test cases include:
Validating points calculation based on provided criteria.
Checking corner cases like empty retailer names or items with no descriptions.
Project Structure
bash
Copy code
challenge_fetch/
├── src/
│   ├── main/
│   │   ├── java/com/pradeep/receipt_processor/
│   │   │   ├── controller/         # REST API Controllers
│   │   │   ├── model/              # Data Models (Receipt, Item)
│   │   │   ├── service/            # Business Logic (Points Calculator)
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/pradeep/receipt_processor/ # Unit Tests
├── Dockerfile
├── README.md
└── pom.xml
Error Handling
Invalid Input: Returns HTTP 400 with an error message.
json
Copy code
{
  "error": "Invalid request payload"
}
Receipt Not Found: Returns HTTP 404 if the requested receipt ID does not exist.
json
Copy code
{
  "error": "Receipt not found"
}
