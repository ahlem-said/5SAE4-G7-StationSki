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
         stage('Tests Junit Mockito') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('Code Quality SonarQube') {
                    steps {
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar '
                    }
        }

  

        stage('Nexus Deploy') {
            steps {
                sh 'mvn deploy -DaltDeploymentRepository=deploymentRepo::default::http://localhost:8081/repository/maven-releases/ -Dnexus.user=admin -Dnexus.password=nexus'
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
                    sh 'docker compose up -d'
                }
            }
        }
       
     stage('Sending email'){
         steps {
          mail bcc: '', body: '''Jenkins Notification,
          CI Pipeline with success.
          Cordialement''', cc: '', from: '', replyTo: '', subject: 'CI completed ', to: 'ayachi.emna@esprit.tn'
          }
                  }
    }
}
