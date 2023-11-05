Project Brief
A platform where citizens can lodge complaints and track the status of their redressal.
Task List

Mrityunjoy: User Management Service

Task 1: Set up Spring Boot project for User Management Service.
Task 2: Design API contracts using OpenAPI.
Task 3: Implement CRUD operations for user data.
Task 4: Implement user authentication and authorization using JWT.
Task 5: Write unit tests for user CRUD operations.
Task 6: Write integration tests for authentication.
Task 7: Dockerize the service.
Task 8: Implement API to retrieve and update user profiles.
Task 9: Set up Spring Cloud Service Registry (Eureka).
Task 10: Register the service with Eureka.

Shriharsha : 2: Grievance Management Service

Task 1: Set up Spring Boot project for Grievance Management Service.
Task 2: Design API contracts.
Task 3: Implement grievance creation and basic CRUD operations.
Task 4: Write unit and integration tests.
Task 5: Dockerize the service.
Task 6: Implement API for updating grievance status.
Task 7: Implement APIs to retrieve grievances.
Task 8: Register with Eureka.
Task 9: Implement RabbitMQ message producer for relevant events (e.g., user registration).
Task 10: Set up Spring Cloud Gateway for routing and load balancing.

Vishal: Admin Management Service

Task 1: Set up Spring Boot project for Admin Management Service.
Task 2: Design API contracts.
Task 3: Implement CRUD for admin data.
Task 4: Write unit tests for admin data management.
Task 5: Dockerize the service.
Task 6: Implement API for assigning grievances to admins.
Task 7: Implement API to fetch assigned grievances.
Task 8: Register with Eureka.
Task 9: Implement Spring Cloud Config Server.
Task 10: Write integration tests for grievance assignment functionalities.

Seth Pal: Notification Service

Task 1: Set up Spring Boot project for Notification Service.
Status: Done

Task 2: Design API contracts.
Status:done
Swagger Url: http://localhost:8000/swagger-ui/index.html

Task 3: Implement email and SMS notification sending functionality.
Status:done
Mail send is implemented
SMS sending is implemented

Task 4: Write unit tests for notification sending functionality.
Status: In Progress

Task 5: Dockerize the service.
Done
Create Dockerfile
Build pakg/install, this will generate the jar file
cd {code directory}
docker build -t notification-app.jar
docker run -p 9090:8080 notification-app.jar


Task 6: Implement event listeners for RabbitMQ for sending notifications.
RabbitMQ Admin: http://localhost:15672/

Commands to enable RabbitMQ
C:\Users\Ravit>docker pull rabbitmq:3.11.11-management
C:\Users\Ravit>docker run -d --hostname rabbit --name rabbit-server -p 15762:15672 -p 5672:5672 rabbitmq:3.11.11-management

![]("C:\Users\Ravit\OneDrive\Desktop\rabbitmq_structure.png")

Task 7: Implement API for fetching notification history.
Done

Task 8: Register with Eureka.
Status: Still need to do.

Task 9: Set up a distributed tracing system with Zipkin and Sleuth.
Status: Still need to do.

Task 10: Write integration tests for event listening and notification sending.
Status:
Use H2 db for integration test, SQL is heavy
Setup H2 DB :https://www.baeldung.com/spring-boot-h2-database
H2 DB Console: http://localhost:8000/h2-console


AI: Create one Eureka server in digital ocean server and share with everyone in group


5: Analytics Service

Task 1: Set up Spring Boot project for Analytics Service.
Task 2: Design API contracts.
Task 3: Implement API to generate various reports.
Task 4: Write unit tests for data calculation logic.
Task 5: Dockerize the service.
Task 6: Implement API to fetch statistical data for dashboards.
Task 7: Ensure real-time data consistency in analytical results.
Task 8: Register with Eureka.
Task 9: Implement RabbitMQ for message-driven processing.
Task 10: Write integration tests for reporting APIs.


Reference Reading material

High Level Design
https://www.notificationapi.com/blog/notification-service-design-with-architectural-diagrams

RabbitMQ
https://www.cloudamqp.com/blog/part1-rabbitmq-for-beginners-what-is-rabbitmq.html?gclid=CjwKCAjw1t2pBhAFEiwA_-A-NKnBgz-LJ6AGp9cAZH8XZsc2aDsoIWipsOp0_-yhqQrVjO8hwAAdchoC3p8QAvD_BwE

Eureka
https://spring.io/guides/gs/service-registration-and-discovery/

Zipkin and Sleuth
https://spring.io/blog/2016/02/15/distributed-tracing-with-spring-cloud-sleuth-and-spring-cloud-zipkin





