pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Checking out code from GitHub...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn clean compile'
            }
        }

        stage('Run Selenium Tests on Grid') {
            steps {
                echo 'Running Selenium Tests on Grid...'
                sh 'mvn test -DGRID_URL=http://localhost:4444/wd/hub'
            }
        }

        stage('Publish Test Reports') {
            steps {
                echo 'Publishing Test Reports...'
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed! Check the logs above.'
        }
    }
}
