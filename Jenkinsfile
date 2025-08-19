pipeline {
    agent any

    tools {
        nodejs 'Node18' // Use the NodeJS tool configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Install Newman + Reporter') {
            steps {
                bat '''
                    npm install -g newman
                    npm install -g newman-reporter-html
                '''
            }
        }

        stage('Run Postman Collection') {
            steps {
                bat '''
                    if not exist reports mkdir reports
                    newman run postman_collection.json -e postman_environment.json ^
                        --reporters cli,junit,html ^
                        --reporter-junit-export reports\\newman-results.xml ^
                        --reporter-html-export reports\\newman-report.html
                '''
            }
        }
    }

    post {
        always {
            echo "Publishing test results..."
            junit 'reports/newman-results.xml'
            archiveArtifacts artifacts: 'reports/*.*', fingerprint: true
        }
    }
}
