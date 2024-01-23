pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'Javahome'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/yassinjouao/LabXpert-Api-Rest-Spring-Boot'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean'

            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
        }

        failure {
            echo 'Build failed!'
        }
    }
}

