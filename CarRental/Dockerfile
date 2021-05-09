FROM hirokimatsumoto/alpine-openjdk-11
EXPOSE 7000

ENV DB_PASSWORD=rotari85!
ENV CURRENCY_SVC_URL=http://localhost:7003/ws

ADD /target/*-0.0.1-SNAPSHOT.jar *-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","*-0.0.1-SNAPSHOT.jar"]