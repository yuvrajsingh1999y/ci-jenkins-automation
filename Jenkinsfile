pipeline {
    agent any

    tools {
        // Name must match what you configured under:
        // Manage Jenkins → Global Tool Configuration → NodeJS installations
        nodejs "Node18"
    }

    stages {
        stage('Install Newman') {
            steps {
                bat 'npm install -g newman'
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

            // Publish JUnit XML results
            junit 'reports\\newman-results.xml'

            // Archive Newman HTML report so you can download/view in Jenkins
            archiveArtifacts artifacts: 'reports\\newman-report.html', fingerprint: true
        }
    }
}
