pipeline {
    agent any

    environment {
        tagImage = "danielchungara1/core-system"
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
                   dockerImage = docker.build tagImage
                }
            }
        }
        stage ('push image on aws registry') {
            steps {
                echo '>>> Uploading image...'
                script {
                    docker.withRegistry("public.ecr.aws/b3v5q8o0", "ecr:us-east-1:aws_credentials") {
                      docker.image("core-system").push()
                    }
                }
            }
        }
    }
}