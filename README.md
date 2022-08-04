# MicroserviceProject
In this project I'm going to make microservices with Spring technologies like Eureka and ApiGateway.
## Spring
When we are doing a Microservice there are important components:
### Entity
A class of type Entity indicates a class that, at an abstract level, is correlated with a table in the database. Each object instantiated by this class indicates a tuple of the table itself, containing the information of the latter.
### Repository
A repository is a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects.
### Service
Indicates the business logic
### Controller
A controller is responsible to process the requests of API REST and with this make the responses
## Eureka Server
Eureka is a REST service, mainly used in the AWS cloud, to which it is closely linked. Eureka behaves as a server, whose objective is to register and locate existing microservices, report their location, status and relevant data for each of them. In addition, it facilitates load balancing and fault tolerance.
Finally, thanks to Spring Cloud Netflix, it provides an easy integration with the Netflix OSS (Open Source Software) project for Spring Boot applications.
## Spring Cloud Gateway
An API Gateway is the traffic manager that interacts with the actual data or backend service and enforces policies, authentication and overall access control for API calls to protect valuable data. An API Gateway is the way you control access to your back-end systems and services and was designed to optimize communication between external clients and your back-end services, providing your customers with a seamless experience. An API Gateway ensures the scalability and high availability of your services. It is responsible for routing the request to the appropriate service and sending a response to the requester. An API Gateway maintains a secure connection between your data and APIs, and manages API traffic and requests, including load balancing, both inside and outside your enterprise. The gateway enforces policies, authentication and general access control for API calls to protect valuable data. An API Gateway takes all API calls from clients and routes them to the correct microservice through request routing, composition and protocol translation.
