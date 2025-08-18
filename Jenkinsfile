pipeline {
    agent any

    tools {
        maven 'Maven'    // adjust name to your Maven installation
        nodejs 'NodeJS'  // adjust name to your NodeJS installation
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/yuvrajsingh1999y/ci-jenkins-automation'
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Validate JSON Files') {
            steps {
                script {
                    try {
                        bat 'node -e "JSON.parse(require(\'fs\').readFileSync(\'postman_environment.json\', \'utf8\'))"'
                        echo "✅ postman_environment.json is valid JSON"
                    } catch (err) {
                        error "❌ postman_environment.json is invalid. Please fix JSON syntax (trailing comma, etc)."
                    }
                }
            }
        }

        stage('Run Postman Tests') {
            steps {
                bat '"C:\\Users\\junja\\AppData\\Roaming\\npm\\newman.cmd" run postman_collection.json -e postman_environment.json --reporters cli,junit,html --reporter-junit-export reports\\newman-results.xml --reporter-html-export reports\\newman-report.html'
            }
        }
    }

    post {
        always {
            junit 'reports/newman-results.xml'
            publishHTML(target: [
                reportDir: 'reports',
                reportFiles: 'newman-report.html',
                reportName: 'Postman HTML Report'
            ])
        }
    }
}
