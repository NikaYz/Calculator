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
                    // Optional: run the image in non-interactive mode to verify it works
                    sh "docker run --rm ${IMAGE_NAME} java -jar target/CalculatorProject-1.0-SNAPSHOT.jar"
                }
            }
        }

        // Optional stage: push to Docker Hub
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
            echo '✅ Build, Docker image, and tests successful!'
            emailext(
                to: 'yourteam@example.com',
                subject: "✅ SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                <h3>Build Successful!</h3>
                <p>Project: ${env.JOB_NAME}<br>
                Build Number: ${env.BUILD_NUMBER}<br>
                <a href="${env.BUILD_URL}">Click here for build logs</a></p>
                """,
                mimeType: 'text/html'
            )
        }
        failure {
            echo '❌ Build or Docker stage failed!'
            emailext(
                to: 'yourteam@example.com',
                subject: "❌ FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                <h3>Build Failed!</h3>
                <p>Project: ${env.JOB_NAME}<br>
                Build Number: ${env.BUILD_NUMBER}<br>
                <a href="${env.BUILD_URL}">View details here</a></p>
                """,
                mimeType: 'text/html'
            )
        }
    }
}
