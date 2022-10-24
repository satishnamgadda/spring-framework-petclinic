pipeline {
    agent  { label 'JDK11' }
      parameters {
        choice(name: 'CHOICE', choices: ['REL_INT_1.0'], description: 'CHOICE')
        string(name: 'MAVEN_GOAL', defaultValue: 'package', description: 'mvn goal') 
        
      }
        
    stages {
        stage('vcs') {
            steps {
                mail subject: 'build started',
                     body: 'build started',
                     to: 'qtdevops@gmail.com'
                git branch: "${params.CHOICE}", url: 'https://github.com/satishnamgadda/spring-framework-petclinic.git'
            }

        }
        stage('build') {
            steps {
                sh "/usr/share/maven/bin/mvn ${params.MAVEN_GOAL}"
            }
        }
        stage('artifacts') {
            steps {
            archiveArtifacts artifacts: 'target/*.war', followSymlinks: false
            }
        }
        post {
            always {
                echo 'job completed'
                 mail subject: 'build completed',
                      body: 'build completed',
                      to: 'qtdevops@gmail.com'
            }
            failure {
                 mail subject: 'build failed',
                      body: 'build failed',
                      to: 'qtdevops@gmail.com'
            }
            success {
                 junit '**/surefire-reports/*.xml'
            }
        }
     
    }
    
}