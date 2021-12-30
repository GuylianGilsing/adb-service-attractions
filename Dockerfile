# ============= #
# === Build === #
# ============= #
FROM openjdk:16 AS build

WORKDIR /service-attractions

# Copy all of the source files into our image
COPY . ./

# Create a clean build of the app
RUN ./mvnw clean install

# ================== #
# === Production === #
# ================== #
FROM adoptopenjdk:16-jre AS production

# Retrieve the fresh build and rename it to app.jar
COPY --from=build "/service-attractions/target/attractions_database-microservice-attraction_data-0.0.1-SNAPSHOT.jar" app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
