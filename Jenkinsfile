pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'cowary/art-tracker-back'
        DOCKER_TAG = "${BUILD_NUMBER}"
    }

    parameters {
        string(name: 'GIT_BRANCH', defaultValue: 'main', description: 'Git branch to checkout and build')
    }

    tools {
        maven 'Maven 3.9.11'
        dockerTool 'docker'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build JAR with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_TAG} ."
                sh "docker tag ${DOCKER_IMAGE_NAME}:${DOCKER_TAG} ${DOCKER_IMAGE_NAME}:latest"
            }
        }

        stage('Login to Docker Registry') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-creds',
                                                 passwordVariable: 'DOCKER_PASSWORD_FILE',
                                                 usernameVariable: 'DOCKER_USERNAME')]) {
                    sh '''
                        echo "$DOCKER_PASSWORD_FILE" > /tmp/docker_password.txt
                        docker login -u $DOCKER_USERNAME --password-stdin < /tmp/docker_password.txt
                        rm /tmp/docker_password.txt # Удаляем файл после использования
                    '''
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                sh "docker push ${DOCKER_IMAGE_NAME}:${DOCKER_TAG}"
                sh "docker push ${DOCKER_IMAGE_NAME}:latest"
            }
        }
    }

    post {
        success {
            echo "Docker image ${DOCKER_IMAGE_NAME}:${DOCKER_TAG} successfully built and pushed!"
        }
        failure {
            echo "Pipeline failed!"
        }
    }
}