pipeline {
    agent any
    tools {
        maven 'Maven' // Must match Maven tool name in Jenkins
    }
    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/yuvrajsingh1999y/ci-jenkins-automation.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Run Postman API Tests') {
            steps {
                echo "Running Postman API tests..."
                // Create reports folder
                bat 'mkdir reports'
                // Run Postman tests with both JUnit & HTML reports
                bat """
                newman run postman_collection.json -e postman_environment.json ^
                --reporters cli,junit,html ^
                --reporter-junit-export reports/newman-results.xml ^
                --reporter-html-export reports/newman-report.html
                """
            }
        }
    }
    post {
        always {
            // Publish Selenium JUnit reports
            junit '**/target/surefire-reports/*.xml'
            // Publish Postman JUnit reports
            junit 'reports/newman-results.xml'

            // Archive HTML report so it can be downloaded
            archiveArtifacts artifacts: 'reports/newman-report.html', followSymlinks: false
        }
    }
}
