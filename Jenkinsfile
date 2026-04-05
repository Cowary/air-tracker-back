pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'cowary/art-tracker-back'
        SONAR_HOST_URL = "http://192.168.1.77:9000"
        FORGEJO_REGISTRY = '192.168.1.77:3002'
        FORGEJO_IMAGE = "${FORGEJO_REGISTRY}/cowary/art-tracker-back"
    }

    parameters {
        string(name: 'GIT_BRANCH', defaultValue: 'master', description: 'Git branch to checkout and build')
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
                        returnStdout: true
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
                        mvn clean verify
                    """
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    withCredentials([string(credentialsId: 'sonarqube-token', variable: 'SONAR_TOKEN')]) {
                        sh """
                            mvn clean verify sonar:sonar -P sonar \
                              -Dsonar.token=\$SONAR_TOKEN \
                              -Dsonar.sources=src/main/java \
                              -Dsonar.tests=src/test/java \
                              -Dsonar.java.binaries=target/classes \
                              -Dsonar.junit.reportPaths=target/surefire-reports \
                              -Dsonar.jacoco.reportPaths=target/jacoco.exec \
                              -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                        """
                    }
                }
                // Опционально: ожидание прохождения Quality Gate
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: false
                }
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

        stage('Deploy to Home Server') {
            steps {
                sshagent(credentials: ['s2-server-ssh']) {
                    sh """
                        ssh -o StrictHostKeyChecking=no sasha@192.168.1.77 '
                            cd /home/sasha/docker/art-tracker &&
                            docker compose pull &&
                            docker compose up -d
                        '
                    """
                }
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