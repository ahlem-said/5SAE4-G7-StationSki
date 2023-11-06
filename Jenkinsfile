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
       
    stage('Sending email') {
    steps {
        mail(
            to: 'ayachi.emna@esprit.tn',
            subject: 'Rapport de construction du projet Jenkins',
            body: '''Cher Équipe,

            La pipeline CI/CD du projet "Station Ski" a été exécutée avec succès.

            Détails de la construction :
            - Nom du projet : Station Ski
            - Statut de construction : Success
            - Utilisateur ayant déclenché la construction : Emna Ayachi
            - Date de début de construction : 2023-10-12 09:30:00
            - Date de fin de construction : 2023-11-09 10:00:00
            
            Cordialement,
            Votre serveur Jenkins'''
        )
    }
}
    }
}
