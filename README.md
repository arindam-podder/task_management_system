
Task Management System RESTful API
This project is a RESTful API for managing tasks. It allows users to create, read, update, and delete tasks efficiently.

Table of Contents
  1-Technologies Used
  2-Getting Started
  3-Prerequisites
  4-Cloning the Repository
  5-Configuration
  6-Running the Application
  7-Using Docker
  8-Starting with Docker Compose
  9-API Endpoints

Technologies Used
Java 17: The main programming language for the backend.
Maven: Build automation tool for dependency management.
MySQL 8+: Database for storing task information.
IntelliJ IDE: Recommended IDE to develop and run the project.
Getting Started
Prerequisites
Before you begin, ensure you have the following installed on your machine:

Java 17: Download Java
Maven: Install Maven
MySQL 8+: Install MySQL
IntelliJ IDE: Download IntelliJ

Cloning the Repository
Open your terminal or command prompt and run the following command to clone the repository:
Copy
git clone https://github.com/<your-username>/<repository-name>.git
(Replace <your-username> and <repository-name> with your GitHub username and the repository name.)

Configuration
Update MySQL Database Information:
Navigate to src/main/resources and open the application.properties file.
Update the MySQL database connection settings:
  spring.datasource.url=jdbc:mysql://localhost:3306/<your_database>
  spring.datasource.username=<your_username>
  spring.datasource.password=<your_password>
Create the Database: 
  Ensure that the specified database exists in your MySQL server. You can create it using the following command:
  CREATE DATABASE <your_database>;
Running the Application
  Using IntelliJ IDE:
  Open the project in IntelliJ.
  Run the application from the IDE by clicking on the run button or by right-clicking the main application class.
Using Command Line:
  Navigate to the project directory and create a JAR file using Maven:
  mvn clean package
  After the build is complete, run the application using:
  java -jar target/<your-jar-file>.jar
  (Replace <your-jar-file> with the actual name of the JAR file that was created.)

Using Docker
  Inside the project directory, you'll find the Dockerfile and docker-compose.yml files for running the application within Docker containers.
  Starting with Docker Compose
  Start the Services:
  Open a terminal and navigate to the directory containing your docker-compose.yml file.
  Run the following command to start all services:
  docker-compose up --build
  Access the Application:
  
  Open a web browser and go to http://localhost:8080/tms to access your Spring Boot application.


API Endpoints
You can explore the available API endpoints through the following Postman collection: https://api.postman.com/collections/19852208-ac9329f4-fbb8-47b0-beb5-f27663fe9bb7?access_key=PMAT-01J8W4PRPAQEZD0G2FT5E02XQV
postman collections :-  https://api.postman.com/collections/19852208-ac9329f4-fbb8-47b0-beb5-f27663fe9bb7?access_key=PMAT-01J8W4PRPAQEZD0G2FT5E02XQV


if any issue fell free to connect with me 
mail : iampodder@gmail.com / iamdynamitethegamer@gmail.com

