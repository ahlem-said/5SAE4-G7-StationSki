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
        stage('JUNIT/MOCKITO') {
            steps {
                sh 'mvn test'  
            }
        }
          stage('SonarQube') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dmaven.test.skip=true'
            }
        }
        stage('Nexus Deploy') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true'  
            }
        }
    }
}
