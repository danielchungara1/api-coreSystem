pipeline {
    agent any

    stages {
		stage ("build") {
            agent {
                docker {
                    image 'openjdk:16-jdk-alpine'
                    args '-v "$PWD":/app'
                    reuseNode true
                }
            }
        	steps {
            	echo 'Building source code...'
            	sh 'chmod +x ./gradlew'
            	sh './gradlew clean build'
            }
        }
    }
}