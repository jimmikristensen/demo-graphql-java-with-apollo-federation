FROM openjdk:14-alpine
COPY build/libs/image_service-*-all.jar image_service.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "image_service.jar"]
