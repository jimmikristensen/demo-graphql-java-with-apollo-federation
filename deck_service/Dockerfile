FROM openjdk:14-alpine
COPY build/libs/deck-*-all.jar deck.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "deck.jar"]
