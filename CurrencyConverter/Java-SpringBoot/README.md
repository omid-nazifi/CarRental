We used Contract-First development style for implementing the exchange service because Spring-WS only supports the contract-first development style.
Technologies: 
 - Java 11
 - Spring Boot 2.4.4
 - SOAP and WSDL
 - Maven 3.6.3
 - XJC tool

1. Checkout `https://github.com/omid-nazifi/CarRental.git`
2. Open the Currency Converter (Java_SpringBoot) in the IntelliJ IDEA
3. Run `mvn clean compile` to generate java classes from defined XML schema file (currency.xsd)
4. Run app server (SoapServiceApplication) with the IntelliJ IDEA
5. You can see WSDL on the address http://localhost:8080/ws/currency.wsdl 
