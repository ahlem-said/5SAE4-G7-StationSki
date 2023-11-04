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
                sh ' mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar   '
            }
        }

      stage('JUNIT/MOCKITO ') {
            steps {
                sh 'mvn test'
            }
        }
	  
        
       





}
}
 
     
