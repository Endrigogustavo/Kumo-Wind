FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/api-0.0.1-SNAPSHOT.jar api.jar
EXPOSE 8081

ENV DB_URL=

ENV CLOUD_NAME_CLOUDINARY=
ENV API_KEY_CLOUDINARY=
ENV API_SECRET_CLOUDINARY=

ENTRYPOINT [ "java", "-jar", "api.jar" ]

