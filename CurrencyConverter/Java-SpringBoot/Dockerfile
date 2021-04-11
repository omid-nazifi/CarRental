FROM hirokimatsumoto/alpine-openjdk-11
EXPOSE 8080
ADD /target/currency_converter-2.0.jar currency_converter-2.0.jar
ENTRYPOINT ["java","-jar","currency_converter-2.0.jar"]