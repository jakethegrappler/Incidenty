# Použij obraz s Java 21
FROM eclipse-temurin:21-jdk AS build

# Nastav pracovní adresář
WORKDIR /app

# Zkopíruj celý projekt
COPY . .


RUN chmod +x mvnw

# Buildni aplikaci (bez testů)
RUN ./mvnw clean package -DskipTests

# Spouštěcí fáze (menší image)
FROM eclipse-temurin:21-jre

WORKDIR /app

# Zkopíruj .jar z předchozí fáze
COPY --from=build /app/target/*.jar app.jar

# Spusť aplikaci
ENTRYPOINT ["java", "-jar", "app.jar"]
