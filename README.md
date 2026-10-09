# Real-Time Data Plotting with Spring Boot, GraalVM Polyglot (R), and MongoDB

This project reads dataset records from MongoDB, processes them dynamically using **GraalVM Polyglot (R Engine)** with the `lattice` library, and serves a real-time updating SVG plot via a Spring Boot web controller.

##  Features
- **Spring Boot (2.7.18)** backend architecture.
- **Spring Data MongoDB** integration for data retrieval from collections.
- **GraalVM 22.3.0 SDK** with Polyglot support to seamlessly evaluate R scripts (`xyplot`).
- **Dynamic Web Streaming:** Automatically updates and renders graphical frames via a web endpoint.

##  Tech Stack
- Java / GraalVM CE 17 (VM 22.3.0)
- Spring Boot & Spring Data MongoDB
- GraalVM Polyglot (R Language Engine)
- MongoDB & MongoDB Compass
- Gradle

##  Configuration
Check `src/main/resources/application.properties` for database and server configurations:
  properties
  spring.data.mongodb.uri=mongodb://localhost:27017/bigdata
  server.port=8080

## How to Run
Ensure MongoDB service is running locally.

Import your dataset into your MongoDB Compass instance under the database bigdata and collection project1.

Run the project using Gradle Wrapper from your terminal:  ./gradlew bootRun
Open your browser and navigate to: http://localhost:8080/plot
