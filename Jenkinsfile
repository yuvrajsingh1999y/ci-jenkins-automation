pipeline {
    agent any

    tools {
        maven 'Maven' // Must match Maven installation name in Jenkins global tool config
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/yuvrajsingh1999y/ci-jenkins-automation.git'
            }
        }

        stage('Build Selenium Project') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Run Postman Tests') {
            steps {
                bat '''
                mkdir reports
                "C:\\Users\\junja\\AppData\\Roaming\\npm\\newman.cmd" run postman_collection.json -e postman_environment.json ^
                    --reporters cli,junit,html ^
                    --reporter-junit-export reports\\newman-results.xml ^
                    --reporter-html-export reports\\newman-report.html
                '''
            }
        }
    }

    post {
        always {
            // Collect Selenium JUnit reports
            junit '**/target/surefire-reports/*.xml'

            // Collect Postman JUnit reports
            junit 'reports/newman-results.xml'

            // Archive Postman HTML report
            archiveArtifacts artifacts: 'reports/newman-report.html', onlyIfSuccessful: false
        }
    }
}
