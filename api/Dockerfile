FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/api-0.0.1-SNAPSHOT.jar api.jar
EXPOSE 8081

#ENV MONGO_URI=
#ENV CLOUD_NAME_CLOUDINARY=seu_nome_de_cloudinary
#ENV CLOUDINARY_API_KEY=seu_api_key
#ENV CLOUDINARY_API_SECRET=seu_api_secret

ENTRYPOINT [ "java", "-jar", "api.jar" ]

