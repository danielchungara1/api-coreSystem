#************************************************************
# Building
#************************************************************

# Add Image based on Gradle7 & JDK16
FROM gradle:7.1.1-jdk16-hotspot AS GRADLE_BUILD

# Set working directory
WORKDIR /building-workspace

# Copy source code to working directory
COPY ./ ./

# Build project
RUN gradle build -x test

#************************************************************
# Executing
#************************************************************

# Add Image based on OpenJDK16 & Alpine
FROM openjdk:16-jdk-alpine

# Copy only the artifacts we need from the first stage and discard the rest
COPY --from=GRADLE_BUILD /building-workspace/build/libs/core-system-v1.0.jar /core-system.jar

# Set the startup command to execute the jar
CMD ["java", "-jar", "/core-system.jar"]