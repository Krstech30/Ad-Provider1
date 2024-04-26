FROM openjdk:11

COPY target/Ad-Provider1-0.0.1-SNAPSHOT.jar Ad-Provider1.jar

ENTRYPOINT ["java","-jar","Ad-Provider1.jar"]

Expose 8081