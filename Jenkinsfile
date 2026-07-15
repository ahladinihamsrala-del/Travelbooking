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
                bat 'mvn clean compile'
            }
        }
        stage('Run Selenium Tests on Grid') {
            steps {
                echo 'Running Selenium Tests on Grid...'
                bat 'mvn test -DGRID_URL=http://localhost:4444/wd/hub'
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
    always {
        cucumber buildStatus: 'UNSTABLE',
                fileIncludePattern: '**/cucumber_*.json',
                jsonReportDirectory: 'target/cucumber-reports',
                reportTitle: 'Cucumber Test Report'
    }
    success {
        echo '✅ Pipeline completed successfully!'
    }
    failure {
        echo '❌ Pipeline failed! Check the logs above.'
    }
}
}