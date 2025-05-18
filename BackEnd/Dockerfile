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
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .

RUN chmod +x ./mvnw

# ⚠️ důležité – použij spring-boot:repackage!
RUN ./mvnw clean package spring-boot:repackage -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

