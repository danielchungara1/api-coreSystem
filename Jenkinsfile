pipeline {
    agent any
    environment {
        hostingImage = "https://registry.hub.docker.com"
        nameImage = "danielchungara1/core-system"
        hostCredentials = 'dockerhub_credentials'
        dockerImage = ''
    }
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
            	echo '>>> Building source code...'
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
                echo '>>> Running tests...'
                sh 'chmod +x ./gradlew'
                sh './gradlew test'
            }
        }
        stage ('build docker image') {
            steps {
                echo '>>> Building image...'
                script {
                   dockerImage = docker.build nameImage
                }
            }
        }
        stage ('push image on dockerhub') {
            steps {
                echo '>>> Uploading image...'
                script {
                    docker.withRegistry('hostingImage', 'dockerhub_credentials') {
                       dockerImage.push()
                    }
                }
            }
        }
    }
}