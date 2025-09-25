pipeline {
    agent any

    tools {
        maven 'Maven3'  // Make sure Maven is installed in Jenkins
        jdk 'OpenJDK11' // Make sure JDK is installed in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git(
                    url: 'https://github.com/NikaYz/Calculator.git',
                    branch: 'main',
                    credentialsId: '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'
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
                sh 'mvn package'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        success {
            echo 'Build and tests successful!'
        }
        failure {
            echo 'Build or tests failed!'
        }
    }
}
