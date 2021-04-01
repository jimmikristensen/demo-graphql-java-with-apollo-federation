FROM openjdk:14-alpine
COPY build/libs/image-*-all.jar image.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "image.jar"]
