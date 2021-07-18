# the first stage of our build will use a gradle
FROM gradle:7.1.1-jdk16-hotspot AS GRADLE_BUILD
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


# docker build -t tplate/core-system:1.0-SNAPSHOT .
# docker run -d -p 8080:8080 --network="host" tplate/core-system:1.0-SNAPSHOT

