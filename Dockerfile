FROM openjdk:17-alpine
LABEL authors="bacha"

COPY  build /libs /vet-clinic-1.0-SNAPSHOT.jar

ENTRYPOINT ["top", "-b"]