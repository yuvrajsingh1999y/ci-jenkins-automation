pipeline {
    agent any
    tools {
        maven 'Maven' // Must match the Maven tool name in Jenkins
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
                bat '"C:\\Users\\junja\\AppData\\Roaming\\npm\\newman.cmd" run postman_collection.json -e postman_environment.json --reporters cli,junit,html --reporter-junit-export reports/newman-results.xml --reporter-html-export reports/newman-report.html'
            }
        }
    }
    post {
        always {
            // Publish Selenium test results
            junit '**/target/surefire-reports/*.xml'

            // Publish Postman API test results (JUnit format)
            junit 'reports/newman-results.xml'

            // Archive HTML report for Postman
            archiveArtifacts artifacts: 'reports/newman-report.html', fingerprint:
