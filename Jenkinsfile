pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'cowary/art-tracker-back'
        SONAR_HOST_URL = "http://192.168.1.77:9000"
        FORGEJO_REGISTRY = '192.168.1.77:3002'
        FORGEJO_IMAGE = "${FORGEJO_REGISTRY}/cowary/art-tracker-back"
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
                script {
                    env.DOCKER_TAG = sh(
                        script: "mvn help:evaluate -Dexpression=project.version -q -DforceStdout",
                        returnValue: 'stdout'
                    ).trim()
                }
            }
        }

        stage('Set up JDK') {
            steps {
                // Убедитесь, что JDK 17 (или нужная версия) установлена на агенте
                // Или используйте tool 'OpenJDK 17' если настроена установка в Jenkins
                sh 'java -version'
            }
        }

        stage('Run Test') {
            steps {
                    sh """
                        mvn clean compile test
                    """
            }
        }

//        stage('SonarQube Analysis') {
//            steps {
//                withCredentials([string(credentialsId: 'sonarqube-token', variable: 'SONAR_TOKEN')]) {
//                    sh """
//                        mvn sonar:sonar -P sonar \\
//                          -Dsonar.host.url=${env.SONAR_HOST_URL} \\
//                          -Dsonar.token=\$SONAR_TOKEN
//                    """
//                }
//            }
//        }

        stage('Build JAR with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_TAG} ."
                sh "docker tag ${DOCKER_IMAGE_NAME}:${DOCKER_TAG} ${DOCKER_IMAGE_NAME}:latest"
                sh "docker tag ${DOCKER_IMAGE_NAME}:${DOCKER_TAG} ${FORGEJO_IMAGE}:${DOCKER_TAG}"
                sh "docker tag ${DOCKER_IMAGE_NAME}:${DOCKER_TAG} ${FORGEJO_IMAGE}:latest"
            }
        }

        stage('Docker Login to Forgejo') {
            steps {
                echo 'Вход в Forgejo registry...'
                withCredentials([usernamePassword(
                    credentialsId: 'forgejo-credentials',
                    usernameVariable: 'FORGEJO_USER',
                    passwordVariable: 'FORGEJO_TOKEN'
                )]) {
                    sh "echo $FORGEJO_TOKEN | docker login ${FORGEJO_REGISTRY} -u $FORGEJO_USER --password-stdin"
                }
            }
        }

        stage('Docker Login') {
            when {
                expression { env.DOCKER_HUB_CREDENTIALS_ID != null }
            }
            steps {
                echo 'Вход в Docker Hub...'
                withCredentials([usernamePassword(
                    credentialsId: env.DOCKER_HUB_CREDENTIALS_ID ?: 'docker-hub',
                    usernameVariable: 'DOCKER_HUB_USER',
                    passwordVariable: 'DOCKER_HUB_PASS'
                )]) {
                    sh 'echo $DOCKER_HUB_PASS | docker login -u $DOCKER_HUB_USER --password-stdin'
                }
            }
        }

        // Пуш образа в registry
        stage('Push to Forgejo') {
            steps {
                echo "Пуш образа ${FORGEJO_IMAGE}:${DOCKER_TAG} в Forgejo..."
                sh """
                    docker push ${FORGEJO_IMAGE}:${DOCKER_TAG}
                    docker push ${FORGEJO_IMAGE}:latest
                """
            }
        }

        stage('Push Image') {
            steps {
                echo "Пуш образа ${DOCKER_IMAGE_NAME}:${DOCKER_TAG}..."
                sh """
                    docker push ${DOCKER_IMAGE_NAME}:${DOCKER_TAG}
                    docker push ${DOCKER_IMAGE_NAME}:latest
                """
            }
        }

        stage('Cleanup') {
            steps {
                echo 'Очистка локальных образов...'
                sh """
                    docker rmi ${DOCKER_IMAGE_NAME}:${DOCKER_TAG} || true
                    docker rmi ${DOCKER_IMAGE_NAME}:latest || true
                    docker rmi ${FORGEJO_IMAGE}:${DOCKER_TAG} || true
                    docker rmi ${FORGEJO_IMAGE}:latest || true
                    docker logout ${FORGEJO_REGISTRY} || true
                """
            }
        }

//        stage('Login to Docker Registry') {
//            steps {
//                withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
//                    sh '''
//                        docker login -u "$DOCKER_USERNAME" -p "$DOCKER_PASSWORD"
//                    '''
//                }
//            }
//        }
//
//        stage('Push Docker Image') {
//            steps {
//                sh "docker push ${DOCKER_IMAGE_NAME}:${DOCKER_TAG}"
//                sh "docker push ${DOCKER_IMAGE_NAME}:latest"
//            }
//        }
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