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
                sh 'mvn clean '
            }
        }
        stage('MVN COMPILE ') {
            steps {
                sh 'mvn  compile'
            }
        }
           
        stage('Artifact Construction') {
            steps{
                	sh "mvn -B -DskipTests package "
            }
        }
       stage('Sonarqube ') {
            steps {
                sh ' mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dsonar.host.url=http://http://192.168.2.14:9000 -Dsonar.token=sqp_563cd22947b1be82c80a56468317bb330ada56fd '
            }
        }
         stage('JUNIT/MOCKITO ') {
            steps {
                sh 'mvn test'
            }
        }
        
       
        stage('Deploy to Nexus') {
            steps {
                       withCredentials([usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'admin', passwordVariable: 'nexus')]) {     
                    sh 'mvn deploy -DaltDeploymentRepository=deploymentRepo::default::http://localhost:8081/repository/maven-releases/ -Dnexus.user=admin -Dnexus.password=nexus'
                }
            }
    }

        stage('Build Docker Image') {
                      steps {
                          script {
                            sh 'docker build -t ahlemsa/gestionski .'
                          }
                      }
                  }

        stage('login dockerhub') {
          steps {
	     sh 'docker login -u ahlemsa --password dckr_pat_jl9D0V6cMYKn3fjLpqZCL5ATkns' }}

	  stage('Push Docker Image') {
            steps {
              sh 'docker push ahlemsa/gestionski' }
		  }


	 stage('Run Spring && MySQL Containers') {
          steps {
          script {
              sh 'docker-compose up -d'}
                                 }
                            

 }





}
}
 
     
