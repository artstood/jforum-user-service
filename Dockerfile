FROM gradle:7.4.0-jdk17-alpine AS BUILD

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build -x test --no-daemon || return 1

FROM openjdk:17-jdk-slim AS DEPLOY

WORKDIR /app

COPY --from=BUILD /home/gradle/src/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
