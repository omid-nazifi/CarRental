### Definition:
CurrencyConverter is a microservice which is used to calculate different currencies based on dollar. 
It connects to [*European Central Bank*](http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml) and receives current rates.

### Technologies:
 - Java 11
 - Spring Boot 2.4.4
 - Springframework kafka
 - Maven 3.6.3

### Getting Started
:white_check_mark: Before run the microservice you have to run [Kafka container](../../Kafka/README.md)
1. Checkout `https://github.com/omid-nazifi/CarRental.git`
2. Open the Currency Converter (Java_SpringBoot) in the IntelliJ IDEA
3. Run `mvn clean install` to build project
4. Run app server (ConverterServiceApplication) with the IntelliJ IDEA
