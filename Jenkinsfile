pipeline {

    agent any

    stages {

        stage('Clone Repository') {

            steps {

                git branch: 'master',
                    url: 'https://github.com/Nagarajukasarla/jsonholder-automation.git'
            }
        }


        stage('Build Docker Image') {

            steps {

                sh 'docker build -t api-framework .'
            }
        }


        stage('Run API Tests') {

            steps {

                sh '''
                    docker run --rm \
                    --name api-container \
                    api-framework
                '''
            }
        }
    }
}