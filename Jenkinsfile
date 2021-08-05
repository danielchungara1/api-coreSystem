 /* Declarative Pipeline */
pipeline {

    agent any

    environment {
        hostingURL = "https://registry.hub.docker.com"
        hostingCredentials = 'dockerhub_credentials'
        nameImage = "danielchungara1/core-system"
        versionImage = "latest"
        dockerImage = ''
    }

    stages {
		stage ('build source code') {
            agent {
                docker {
                    image 'openjdk:16-jdk-alpine'
                    args '-u root -v "$PWD":/app'
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
                    args '-u root -v "$PWD":/app'
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
                   dockerImage = docker.build nameImage + ":$versionImage"
                }
                sh 'docker image prune -f'
            }
        }
        stage ('push image on aws registry') {
            steps {
                echo '>>> Uploading image...'
                script {
                    docker.withRegistry("$hostingURL", "$hostingCredentials") {
                      dockerImage.push()
                    }
                }
                sh 'docker image prune -f'
            }
        }
    }
}

def remote = [:]
remote.name = "ec2-3-135-182-125.us-east-2.compute.amazonaws.com"
remote.host = "3.14.172.153"
remote.allowAnyHosts = true
remote.user= "ubuntu"

node {
    withCredentials([sshUserPrivateKey(credentialsId: 'aws_credentialsPrivateKey', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: '')]) {
        remote.identityFile = identity
        stage("start services") {
            sshCommand remote: remote, command: 'cd /home/ubuntu/api-coreSystem/ && git checkout develop && git pull'
            sshCommand remote: remote, command: 'aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 597217115475.dkr.ecr.us-east-2.amazonaws.com'
            sshCommand remote: remote, command: 'cd /home/ubuntu/api-coreSystem/ && docker-compose pull && docker-compose up -d'
            sshCommand remote: remote, command: 'docker image prune -f'
        }
    }
}
