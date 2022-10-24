pipeline {
    agent  { label 'JDK11' }   
    stages {
        stage('vcs') {
            steps {
                   mail subject: 'build started',
                     body: 'build started',
                     to: 'qtdevops@gmail.com'
                git branch: "REL_INT_1.0", url: 'https://github.com/satishnamgadda/spring-framework-petclinic.git'
            }

        }
        stage('artifactory configuaration') {
            steps {
                rtMavenDeployer (
                   id : "MVN_DEFAULT",
                   releaseRepo : "spc-libs-release-local",
                   snapshotRepo : "spc-libs-snapshot-local",
                   serverId : "JFROG-SPC"
                )

            }
        }
        stage('Exec Maven') {
            steps {
                rtMavenRun(
                    pom : "pom.xml",
                    goals : "package",
                    tool : "mvn",
                    deployerId : "MVN_DEFAULT"
                )
          
            }
        }
         stage('Build the Code') {
            steps {
               withSonarQubeEnv('SONAR') {
                    sh script: 'mvn clean package sonar:sonar'
                }
            }
        }
        stage('publish build info') {
            steps {
               rtPublishBuildInfo(
                serverId : "JFROG-SPC"
               )
            }
        }
    }
    
}