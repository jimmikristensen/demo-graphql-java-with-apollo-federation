FROM openjdk:14-alpine
COPY build/libs/entity-*-all.jar entity.jar
EXPOSE 8090
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "entity.jar"]
