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

        stage('ARTIFACT CONSTRUCTION') {
            steps {
                sh 'mvn -B -DskipTests package'
            }
        }
         stage('JUNIT/MOCKITO') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('Code Quality Check via SonarQube') {
                    steps {
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dmaven.test.skip=true'
                    }
        }


        stage('JUNIT/MOCKITO') {
            steps {
                sh 'mvn test'
            }
        }
  

        stage('Nexus Deploy') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip=true'
            }
        }

        stage('Build Docker Image') {
                   steps {
                       script {
                           sh 'docker build -t emnaayachi/course .'
                       }
                   }
               }

               stage('Docker Hub') {
                   steps {
                       script {
                           sh 'docker login -u emnaayachi -p dckr_pat_r126eCxHuD-1RxG9UGWC8cPAbmI'
                       }
                   }
               }

        stage('Push Docker Image') {
            steps {
                script {
                    sh 'docker push emnaayachi/course:latest'
                }
            }
        }

        stage('Docker compose') {
            steps {
                script {
                    sh 'docker-compose up -d'
                }
            }
        }
    }
}
