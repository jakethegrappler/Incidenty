#java 21
FROM eclipse-temurin:21-jdk AS build


WORKDIR /app

COPY . .


RUN chmod +x mvnw

# Build aplikace(no tests)
RUN ./mvnw clean package -DskipTests

# Spouštěcí fáze (menší image)
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .

RUN chmod +x ./mvnw

RUN ./mvnw clean package spring-boot:repackage -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

