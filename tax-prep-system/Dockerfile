FROM openjdk:17-jdk-slim

EXPOSE 8080

WORKDIR /app

COPY target/tax-prep-system-0.0.1-SNAPSHOT.jar /app/tax-prep-system-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "tax-prep-system-0.0.1-SNAPSHOT.jar"]