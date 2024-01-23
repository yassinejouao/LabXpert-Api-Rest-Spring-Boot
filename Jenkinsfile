pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'Javahome'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/adilfettach/craftStore-With-spring-core-testing'
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

