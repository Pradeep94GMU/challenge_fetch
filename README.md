# challenge_fetch
Receipt Processor Application
Table of Contents
Project Overview
Technologies Used
Features
Getting Started
Prerequisites
Installation
How to Run
Running with Maven
Running with Docker
API Endpoints
Example Usage
Testing
Project Structure
Future Enhancements
License
Project Overview
The Receipt Processor application is a Spring Boot service that processes receipts and calculates points based on various criteria. The points calculation is based on the retailer's name, total amount, item descriptions, purchase date, and time. The service stores data in memory and does not require an external database.

Technologies Used
Java 17
Spring Boot 3
Maven
Docker
Features
Calculate points based on retailer name, total amount, item details, purchase date, and time.
In-memory storage of receipt data.
REST API for receipt processing.
Docker support for easy setup and execution.
Getting Started
Prerequisites
Java 17 or higher
Maven 3.8+
Docker
Installation
Clone the repository:
bash
Copy code
git clone https://github.com/Pradeep94GMU/challenge_fetch.git
cd challenge_fetch
Build the project using Maven:
bash
Copy code
./mvnw clean install
How to Run
Running with Maven
Start the Spring Boot application:
bash
Copy code
./mvnw spring-boot:run
The application will start on http://localhost:8080.
Running with Docker
Build the Docker image:
bash
Copy code
docker build -t receipt-processor-app .
Run the Docker container:
bash
Copy code
docker run -p 8080:8080 receipt-processor-app
The application will be available at http://localhost:8080.
API Endpoints
1. Process Receipt
URL: /api/v1/receipts/process
Method: POST
Description: Calculates points based on the receipt details provided in the request.
Request Body:
json
Copy code
{
  "retailer": "Target",
  "total": "35.35",
  "items": [
    {"shortDescription": "Cereal", "price": "2.99"},
    {"shortDescription": "Milk", "price": "3.49"}
  ],
  "purchaseDate": "2023-11-01",
  "purchaseTime": "15:30"
}
Response:
json
Copy code
{
  "points": 27
}
2. Retrieve Points
URL: /api/v1/receipts/{id}/points
Method: GET
Description: Retrieves the points for a specific receipt by ID.
Response:
json
Copy code
{
  "points": 27
}
Example Usage
Using Curl
bash
Copy code
curl -X POST http://localhost:8080/api/v1/receipts/process \
-H "Content-Type: application/json" \
-d '{"retailer": "Target", "total": "35.35", "items": [{"shortDescription": "Cereal", "price": "2.99"}, {"shortDescription": "Milk", "price": "3.49"}], "purchaseDate": "2023-11-01", "purchaseTime": "15:30"}'
Using Postman
Open Postman and create a new POST request.
URL: http://localhost:8080/api/v1/receipts/process
In the Body tab, select raw and set it to JSON.
Paste the request body and send the request.
Testing
You can run unit tests using Maven:
bash
Copy code
./mvnw test
For manual testing, use curl or tools like Postman as described above.
Project Structure
bash
Copy code
challenge_fetch/
├── src/
│   ├── main/
│   │   ├── java/com/pradeep/receipt_processor/
│   │   │   ├── controller/         # REST Controllers
│   │   │   ├── model/              # Data Models
│   │   │   ├── service/            # Service Layer
│   │   └── resources/
│   │       └── application.properties
│   ├── test/java/com/pradeep/receipt_processor/ # Unit Tests
├── Dockerfile
├── README.md
└── pom.xml
