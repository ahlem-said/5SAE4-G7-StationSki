pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                git branch: 'Emnaayachi-5SAE4-G7',
                url: 'https://github.com/ahlem-said/5SAE4-G7-StationSki.git'
            }
        }

        stage('MVN CLEAN AND COMPILE') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('SonarQube') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }
}
