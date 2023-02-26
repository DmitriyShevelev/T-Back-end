FROM openjdk:17
EXPOSE 9090
ADD ./target/pulse-monitoring-gateway-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]