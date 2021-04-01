FROM openjdk:14-alpine
COPY build/libs/search-*-all.jar search.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "search.jar"]
