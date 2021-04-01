FROM openjdk:14-alpine
COPY build/libs/deck_service-*-all.jar deck_service.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "deck_service.jar"]
