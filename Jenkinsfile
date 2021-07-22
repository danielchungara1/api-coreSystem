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
            	echo 'Common tasks...'
            	sh 'chmod +x ./gradlew'
            }
        	steps {
            	echo 'Building source code...'
            	sh './gradlew clean build -x'
            }
            steps {
                echo 'Running tests...'
                sh './gradlew test'
            }
        }
    }
}