# Base Image

FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

COPY . .

# Download dependencies first
RUN mvn dependency:resolve

#Execute tests
CMD ["mvn", "clean", "test"]