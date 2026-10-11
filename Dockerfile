FROM openjdk:17-alpine
LABEL authors="bacha"

COPY  build /libs /registration-1.0-SNAPSHOT.jar

ENTRYPOINT ["top", "-b"]