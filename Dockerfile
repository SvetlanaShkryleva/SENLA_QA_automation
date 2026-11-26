FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .
COPY .mvn .mvn

RUN mvn dependency:go-offline

COPY src ./src

CMD ["mvn", "test"]