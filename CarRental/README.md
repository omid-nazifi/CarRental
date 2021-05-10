### Definition:
CarRental include some microservices which are responsible to response client's requests. 
Here there are three microservices:
 - Customer Microservice
 - Vehicle microservice
 - API Gateway

### Technologies:
- Java 11
- Spring Boot 2.4.4
- Springframework kafka
- Maven 3.6.3

### Getting Started
:white_check_mark: Before run the microservice you have to run [Kafka container](../../Kafka/README.md)
1. Checkout `https://github.com/omid-nazifi/CarRental.git`
2. Open the inner CarRental in the IntelliJ IDEA
3. Run `mvn clean install` to build all project and its modules
4. For each microservice exist its own main Application java file that should be run. forr example ApiGatewayApplication.
