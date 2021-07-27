pipeline {
    agent any

    environment {
        tagImage = "core-system"
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
                    docker.withRegistry("https://597217115475.dkr.ecr.us-east-2.amazonaws.com", "ecr:us-east-2:aws_credentials") {
                      docker.image("core-system").push()
                    }
                }
            }
        }
        stage ('start/reload services') {
            steps {
                echo '>>> Starting services...'
                steps {
                    echo '>>> Starting services...'
                    sshagent (credentials: ['rootAWS_credentials']) {
                        sh 'ssh ubuntu@3.135.182.125 cd ~/api-coreSystem"'
                      }
                }
            }
        }
    }
}