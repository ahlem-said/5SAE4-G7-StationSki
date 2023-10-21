pipeline {
    agent any
  
    stages {
        stage('GIT') {
            steps {
                git branch: 'SaidAhlem-5SAE4-G7',
                url: 'https://github.com/ahlem-said/5SAE4-G7-StationSki.git'
            }
        }
        
        stage('MVN CLEAN ') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('MVN COMPILE ') {
            steps {
                sh 'mvn  compile'
            }
        }
         stage('MVN SONAQUBE ') {
            steps {
                sh 'mvn  sonarqube'
            }
        }
    }
}
