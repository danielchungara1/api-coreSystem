# the first stage of our build will use a gradle
FROM gradle:7.1.1-jdk16-hotspot AS GRADLE_BUILD

# all commands are running in working dir
WORKDIR /build-workspace

# copy the build.gradle and src code to the container
COPY ./ ./

# package our application code (generates .jar file without running tests)
RUN gradle assemble

# the second stage of our build will use open jdk on alpine
FROM openjdk:16-jdk-alpine

# copy only the artifacts we need from the first stage and discard the rest
COPY --from=GRADLE_BUILD /build-workspace/build/libs/core-system-0.0.1-SNAPSHOT.jar /core-system.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/core-system.jar"]


# Webservice Image
# docker build -t tplate/core-system:1.0-SNAPSHOT .
# docker run -d -p 8080:8080 --network="host" tplate/core-system:1.0-SNAPSHOT


# Postgres Image
# docker run --name postgresql13_v2 -p 5432:5432 -e POSTGRES_PASSWORD=root -d postgres:13.3-alpine
# docker start postgresql13_v2

# Upload image to dockerhub.com
# docker tag [id_image] [username_docker]/core-system:v1
# docker push [repository]:[tag]