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

        stage('Run Tests in Parallel') {
            parallel {
                stage('Selenium Tests') {
                    steps {
                        bat 'mvn clean install'
                        bat 'mvn test'
                    }
                }
                stage('Postman Tests') {
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
