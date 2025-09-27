pipeline {
    agent any

    tools {
        maven 'Maven3'  // Make sure Maven is installed in Jenkins
        jdk 'OpenJDK11' // Or JDK17 if your project uses Java 17
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
                    docker.withRegistry('https://index.docker.io/v1/', 'dckr_pat_y87w5YbPf2Z3YetzYiKBejPFSM4') {
                        sh "docker tag ${IMAGE_NAME} nikayz/${IMAGE_NAME}"
                        sh "docker push nikayz/${IMAGE_NAME}"
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        success {
            echo 'Build, Docker image, and tests successful!'
        }
        failure {
            echo 'Build or Docker stage failed!'
        }
    }
}

// pipeline {
//     agent any

//     tools {
//         maven 'Maven3'  // Make sure Maven is installed in Jenkins
//         jdk 'OpenJDK11' // Make sure JDK is installed in Jenkins
//     }

//     stages {
//         stage('Checkout') {
//             steps {
//                 git(
//                     url: 'https://github.com/NikaYz/Calculator.git',
//                     branch: 'main',
//                     credentialsId: 'jenkins-github'
//                 )
//             }
//         }

//         stage('Build') {
//             steps {
//                 sh 'mvn clean compile'
//             }
//         }

//         stage('Test') {
//             steps {
//                 sh 'mvn test'
//             }
//         }

//         stage('Package') {
//             steps {
//                 sh 'mvn package'
//             }
//         }
//     }

//     post {
//         always {
//             archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
//         }
//         success {
//             echo 'Build and tests successful!'
//         }
//         failure {
//             echo 'Build or tests failed!'
//         }
//     }
// }
