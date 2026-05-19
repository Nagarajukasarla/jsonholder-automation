pipeline {

    agent any

    stages {

        stage('Clone Repository') {

            steps {

                git branch: 'master',
                    url: 'https://github.com/Nagarajukasarla/jsonholder-automation.git'
            }
        }


        stage('Run API Tests') {

            steps {

                sh '''
                    mvn clean test
                '''
            }
        }
    }
}