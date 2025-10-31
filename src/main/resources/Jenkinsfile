pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'Cowary/ArtTrackerBack'
        DOCKER_TAG = "${BUILD_NUMBER}"
    }

    parameters {
        string(name: 'GIT_BRANCH', defaultValue: 'main', description: 'Git branch to checkout and build')
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
                withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                    sh "echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin"
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