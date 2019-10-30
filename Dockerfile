FROM openjdk:8-jdk-alpine as build-stage
ADD . /cors
WORKDIR /cors
RUN chmod +x ./gradlew && ./gradlew shadowJar

FROM openjdk:8-jdk-alpine as exec-stage
COPY --from=build-stage /cors/build/libs/cors-0.0.1-all.jar .
ENTRYPOINT ["java", "-jar", "cors-0.0.1-all.jar"]