pipeline {
    agent any

    stages {
		stage ('build source code') {
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
            	sh './gradlew clean build -x test'
            }
        }
        stage ('run tests') {
            agent {
                docker {
                    image 'openjdk:16-jdk-alpine'
                    args '-v "$PWD":/app'
                    reuseNode true
                }
            }
            steps {
                echo 'Running tests...'
                sh 'chmod +x ./gradlew'
                sh './gradlew test'
            }
        }
        stage ('build docker image') {
            steps {
                sh 'docker build -t danielchungara1/core-system .'
            }
        }
        stage ('deploy locally') {
            steps {
                sh 'docker run -d -p 8090:8080 danielchungara1/core-system'
            }
        }
    }
}