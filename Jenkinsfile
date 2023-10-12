pipeline {
    agent any

    stages {
        stage('Récupération du code') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'Emnaayachi-5SAE4-G7']]])
            }
        }

        stage('Nettoyage et Compilation') {
            steps {
                sh 'mvn clean compile'
            }
        }
    }
}
