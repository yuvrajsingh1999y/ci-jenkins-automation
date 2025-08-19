pipeline {
    agent any

    tools {
        nodejs "NodeJS"   // Use the NodeJS tool you configured in Jenkins Global Tool Configuration
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/yuvrajsingh1999y/ci-jenkins-automation'
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

        stage('Run Postman Tests') {
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
            echo 'Publishing test results...'
            junit 'reports/newman-results.xml'
            archiveArtifacts artifacts: 'reports/*.*', allowEmptyArchive: true
        }
    }
}
