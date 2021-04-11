FROM hirokimatsumoto/alpine-openjdk-11
EXPOSE 8080
ADD /target/currency_converter-*.jar currency_converter-*.jar
ENTRYPOINT ["java","-jar","currency_converter-*.jar"]