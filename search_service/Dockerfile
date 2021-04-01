FROM openjdk:14-alpine
COPY build/libs/search_service-*-all.jar search_service.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "search_service.jar"]
