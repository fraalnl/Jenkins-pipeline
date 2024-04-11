#!/usr/bin/env groovy
// Use the line above to colour Groovy syntax in IDE

pipeline {
    agent any

    tools {
        maven "Maven-3.9.5"
    }

    environment {
        SONARQUBE_TOKEN = credentials('sonar-token')
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_PROJECT_KEY = 'org.example:JenkinsPipeline'
    }

    stages {
        stage('Build') {
            steps {
                checkout([$class: 'GitSCM', branches: [name: '*/main'], userRemoteConfigs: [[url: 'https://github.com/fraalnl/Jenkins-pipeline.git']]])
                bat "mvn clean package -DskipTests"
            }
            post {
                always {
                    echo 'stage post always'
                }
                success {
                    echo 'pipeline post success'
                }
                failure {
                    echo 'pipeline post failure'
                }
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test jacoco:prepare-agent jacoco:report'
            }
            post {
                always {
                    jacoco(execPattern: '**/target/jacoco.exec')
                    junit '**/target/surefire-reports/*.xml'
                }
                success {
                    archiveArtifacts '**/target/*.jar'
                }
            }
        }


        stage('SonarQube Analysis') {
            steps {
                bat "mvn sonar:sonar -Dsonar.token=%SONARQUBE_TOKEN% -Dsonar.host.url=%SONAR_HOST_URL% -Dsonar.projectKey=%SONAR_PROJECT_KEY% -Dsonar.language=java -Dsonar.sources=src/main/java -Dsonar.exclusions=**/target/**/*.*"
            }
        }
    }
}