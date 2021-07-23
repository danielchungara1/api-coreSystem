#************************************************************
# Building
#************************************************************

# Add Image based on JDK16
FROM openjdk:16-jdk-alpine

# Set working directory
WORKDIR /building-workspace

# Copy source code to working directory
COPY ./ ./

# Build project
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

#************************************************************
# Executing
#************************************************************

# Copy only the artifacts we need from the first stage and discard the rest
COPY /building-workspace/build/libs/core-system-v1.0.jar /core-system.jar

# Set the startup command to execute the jar
CMD ["java", "-jar", "/core-system.jar"]
