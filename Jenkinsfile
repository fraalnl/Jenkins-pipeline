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
                checkout scm: [
                        $class: 'GitSCM',
                        branches: [[name: 'bugfix']],
                        url: 'https://github.com/fraalnl/Jenkins-pipeline.git'
                ]
                bat 'mvn clean compile'
            }
            post {
                always {
                    echo 'Compile post always'
                }
                success {
                    echo 'Compile post success'
                }
                failure {
                    echo 'Compile post failure'
                }
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts '**/target/*.jar'
                }
            }
        }

        stage('Verify') {
            steps {
                bat 'mvn verify -DskipTests'
            }
            post {
                success {
                    echo 'Verify stage post success'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                bat "mvn sonar:sonar -Dsonar.token=%SONARQUBE_TOKEN% -Dsonar.host.url=%SONAR_HOST_URL% -Dsonar.projectKey=%SONAR_PROJECT_KEY% -Dsonar.language=java -Dsonar.sources=src/main/java -Dsonar.exclusions=**/target/**/*.*"
//                script {
//                    def sonarProperties = [
//                        "sonar.projectKey": "org.example:JenkinsPipeline",
//                        "sonar.projectName": "JenkinsPipeline",
//                        "sonar.host.url": "http://localhost:9000",
//                        "sonar.sources": "src/main/java",
//                        "sonar.tests": "src/test/java",
//                        "sonar.sourceEncoding": "UTF-8",
//                        "sonar.projectVersion": 1.0,
//                        "sonar.language": "java",
//                        "sonar.java.binaries": "target/classes/org/example",
//                        "sonar.exclusions": "**/target/**/*.*",
//                        "sonar.coverage.jacoco.reportPaths": "target/site/jacoco/jacoco.xml"
//                    ]
//
//                    if (env.SONARQUBE_TOKEN) {
//                        sonarProperties["sonar.login"] = env.SONARQUBE_TOKEN
//                    }
//                    withSonarQubeEnv('sonarqube') {
//                        script {
//                            def scanner = tool 'SonarQube Scanner'
//                            withEnv(["PATH+MAVEN=${tool 'Maven-3.9.5'}/bin"]) {
//                                bat "${scanner}\\bin\\sonar-scanner.bat " + sonarProperties.collect {"-D${it.key}=${it.value}" }.join(' ')
//                            }
//                        }
//                    }
//                }
            }
        }
    }
}