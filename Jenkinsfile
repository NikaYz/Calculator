pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'OpenJDK11'
    }

    environment {
        IMAGE_NAME = 'scientific-calculator:1.0'
    }

    stages {
        stage('Checkout') {
            steps {
                git(
                    url: 'https://github.com/NikaYz/Calculator.git',
                    branch: 'main',
                    credentialsId: 'jenkins-github'
                )
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${IMAGE_NAME} ."
                }
            }
        }

        stage('Run Docker Image') {
            steps {
                script {
                    sh "docker run --rm ${IMAGE_NAME} java -jar target/CalculatorProject-1.0-SNAPSHOT.jar"
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'jenkins-docker') {
                        sh "docker tag ${IMAGE_NAME} nikayz/${IMAGE_NAME}"
                        sh "docker push nikayz/${IMAGE_NAME}"
                    }
                }
            }
        }
        
        stage('Deploy with Ansible') {
            steps {
                sh 'ansible-playbook -i deployment/inventory deployment/deploy_calculator.yml --become'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        success {
            mail to: 'adisaraf7373@gmail.com',
                 subject: "Build Success: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Build succeeded! Check details: ${env.BUILD_URL}"
        }
        failure {
            mail to: 'adisaraf7373@gmail.com',
                 subject: "Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Build failed! Check details: ${env.BUILD_URL}"
        }
    }
}
