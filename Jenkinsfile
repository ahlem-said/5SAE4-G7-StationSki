pipeline {
    agent any
  
    stages {
        stage('GIT') {
            steps {
                git branch: 'SaidAhlem-5SAE4-G7',
                url: 'https://github.com/ahlem-said/5SAE4-G7-StationSki.git'
            }
        }
        
        stage('MVN CLEAN AND COMPILE') {
            steps {
                sh 'mvn clean compile'
            }
        }
    }
}
