FROM amazoncorretto:17-alpine

WORKDIR /app

COPY target/*.jar /app/patient-hub-service.jar

EXPOSE 8080

CMD ["java", "-jar", "patient-hub-service.jar"]