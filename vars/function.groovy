def call() {
pipeline {
    agent any  
    stages {
        stage('vcs') {
            steps {
                git branch: "$REL_INT_1.0", url: 'https://github.com/satishnamgadda/spring-framework-petclinic.git'
            }

        }
        stage('build') {
            steps {
                sh "/usr/share/maven/bin/mvn package"
            }
        }
        stage('artifacts') {
            steps {
            archiveArtifacts artifacts: 'target/*.war', followSymlinks: false
            }
        }
        stage('archive results') {
            steps {
                junit '**/surefire-reports/*.xml'
            }
        }
    }
    
}
}