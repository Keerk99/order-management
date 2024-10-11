FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE=target/order-management-0.0.1.jar
COPY ${JAR_FILE} app_order-management.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_order-management.jar"]