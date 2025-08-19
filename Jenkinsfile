pipeline {
    agent any

    tools {
        // 👇 Change this name to match your Jenkins NodeJS installation (Manage Jenkins → Tools → NodeJS)
        nodejs "Node18"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/yuvrajsingh1999y/ci-jenkins-automation'
            }
        }

        stage('Install Dependencies') {
            steps {
                // Install Newman locally
                sh 'npm install -g newman'
            }
        }

        stage('Run Postman Tests') {
            steps {
                // Ensure reports folder exists
                sh 'mkdir -p reports'

                // Run Newman with reports
                sh '''
                newman run postman_collection.json \
                    -e postman_environment.json \
                    --reporters cli,junit,html \
                    --reporter-junit-export reports/newman-results.xml \
                    --reporter-html-export reports/newman-report.html
                '''
            }
        }
    }

    post {
        always {
            // ✅ Collect test reports (must be inside node context)
            junit 'reports/newman-results.xml'

            // Save HTML report
            archiveArtifacts artifacts: 'reports/newman-report.html', fingerprint: true
        }
    }
}
