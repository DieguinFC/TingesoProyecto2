pipeline {
    agent any
    tools {
        gradle 'gradle'
        nodejs 'node'
    }

    stages {
        stage('Checkout Back') {
            steps {
                checkout scmGit(
                    branches: [[name: '*/master']],
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/DieguinFC/TingesoProyecto1']]
                )
            }
        }

        stage('Build Back') {
            steps {
                script {
                    if (isUnix()) {
                        // Cambia al directorio 'backend' antes de ejecutar gradle
                        sh 'cd backend && chmod +x gradlew && ./gradlew clean build'  // Para Unix/Linux
                    } else {
                        // En sistemas Windows (si fuera necesario)
                        bat 'cd backend && gradlew clean build'  // Para Windows
                    }
                }
            }
        }

        stage('Unit Tests Back') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'cd backend && ./gradlew test'  // Para Unix/Linux
                    } else {
                        bat 'cd backend && gradlew test'  // Para Windows
                    }
                }
            }
        }

        stage('Build Docker Image for Backend') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'cd backend && docker build -t shezy1/backimage:latest .'  // Para Unix/Linux
                    } else {
                        bat 'cd backend && docker build -t shezy1/backimage:latest .'  // Para Windows
                    }
                }
            }
        }

        stage('Push Docker Image for Backend') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'DOCKERHUB_PASS', variable: 'DOCKERHUB_PASS')]) {
                        if (isUnix()) {
                            sh 'cd backend && docker login -u shezy1 -p $DOCKERHUB_PASS'  // Para Unix/Linux
                        } else {
                            bat 'cd backend && docker login -u shezy1 -p %DOCKERHUB_PASS%'  // Para Windows
                        }
                    }
                    if (isUnix()) {
                        sh 'cd backend && docker push shezy1/backimage:latest'  // Para Unix/Linux
                    } else {
                        bat 'cd backend && docker push shezy1/backimage:latest'  // Para Windows
                    }
                }
            }
        }

        stage('Checkout Front') {
            steps {
                checkout scmGit(
                    branches: [[name: '*/master']],
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/DieguinFC/TingesoProyecto1']]
                )
            }
        }

        stage('Build Front') {
            steps {
                script {
                    if (isUnix()) {
                        // Cambia al directorio 'vite-project' antes de ejecutar npm
                        sh 'cd vite-project && npm install'  // Para Unix/Linux
                        sh 'cd vite-project && npm run build'  // Para Unix/Linux
                    } else {
                        bat 'cd vite-project && npm install'  // Para Windows
                        bat 'cd vite-project && npm run build'  // Para Windows
                    }
                }
            }
        }

        stage('Build Docker Image for Frontend') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'cd vite-project && docker build -t shezy1/frontimage:latest .'  // Para Unix/Linux
                    } else {
                        bat 'cd vite-project && docker build -t shezy1/frontimage:latest .'  // Para Windows
                    }
                }
            }
        }

        stage('Push Docker Image for Frontend') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'DOCKERHUB_PASS', variable: 'DOCKERHUB_PASS')]) {
                        if (isUnix()) {
                            sh 'cd vite-project && docker login -u shezy1 -p $DOCKERHUB_PASS'  // Para Unix/Linux
                        } else {
                            bat 'cd vite-project && docker login -u shezy1 -p %DOCKERHUB_PASS%'  // Para Windows
                        }
                    }
                    if (isUnix()) {
                        sh 'cd vite-project && docker push shezy1/frontimage:latest'  // Para Unix/Linux
                    } else {
                        bat 'cd vite-project && docker push shezy1/frontimage:latest'  // Para Windows
                    }
                }
            }
        }
    }

    triggers {
        // Activa la construcción automáticamente en cada push al repositorio
        githubPush()
    }
}
