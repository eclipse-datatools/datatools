pipeline {
  options {
    timeout(time: 50, unit: 'MINUTES')
    buildDiscarder(logRotator(numToKeepStr:'10'))
    disableConcurrentBuilds(abortPrevious: true)
  }

  agent {
    label "centos-latest"
  }

  tools {
    maven 'apache-maven-latest'
    jdk 'temurin-jdk21-latest'
  }

  environment {
    CHECKOUT = 'false'
    CLONE_URL = 'https://github.com/eclipse-datatools/datatools'
    CLONE_BRANCH = 'master'
  }

  parameters {
    choice(
      name: 'BUILD_TYPE',
      choices: ['nightly', 'milestone', 'release'],
      description: '''
      Choose the type of build.
      Note that a release build will not promote the build, but rather will promote the most recent milestone build.
      '''
    )

    booleanParam(
      name: 'PROMOTE',
      defaultValue: true,
      description: 'Whether to promote the build to the download server.'
    )
  }

  stages {
    stage('Display Parameters') {
        steps {
            script {
                env.BUILD_TYPE = params.BUILD_TYPE
                if (env.BRANCH_NAME == 'master' || env.BRANCH_NAME == null) {
                  env.WITH_CREDENTIALS = true
                  if (params.PROMOTE) {
                    env.MAVEN_PROFILES = "-Peclipse-sign -Ppromote"
                  } else {
                    env.MAVEN_PROFILES = "-Peclipse-sign"
                  }
                } else {
                  env.WITH_CREDENTIALS = false
                  env.MAVEN_PROFILES = ""
                }
                def description = """
BUILD_TYPE=${env.BUILD_TYPE}
WITH_CREDENTIALS=${env.WITH_CREDENTIALS}
PROMOTE=${env.PROMOTE}
""".trim()
                echo description
                currentBuild.description = description.replace("\n", "<br/>")
            }
        }
    }

    stage('Git Checkout') {
      when {
        environment name: 'CHECKOUT', value: 'true'
      }
      steps {
        script {
          def gitVariables = checkout(
            poll: false,
            scm: [
              $class: 'GitSCM',
              branches: [[name: '*' + "${env.CLONE_BRANCH}"]],
              doGenerateSubmoduleConfigurations: false,
              submoduleCfg: [],
              userRemoteConfigs: [[url: "${env.CLONE_URL}.git" ]]
            ]
          )

          echo "$gitVariables"
          env.GIT_COMMIT = gitVariables.GIT_COMMIT

          env.WITH_CREDENTIALS = true
          if (params.PROMOTE) {
            env.MAVEN_PROFILES = "-Peclipse-sign -Ppromote"
           } else {
             env.MAVEN_PROFILES = "-Peclipse-sign"
           }
        }
      }
    }

    stage('Build') {
      steps {
        script {
          if (env.WITH_CREDENTIALS) {
            sshagent (['projects-storage.eclipse.org-bot-ssh']) {
              mvn()
            }
          } else {
              mvn()
          }
        }
      }

      post {
        always {
          archiveArtifacts artifacts: '**/target/repository/**/*,**/target/*.zip,**/target/work/data/.metadata/.log'
          junit '**/target/surefire-reports/TEST-*.xml'
        }
      }
    }
  }
}

def void mvn() {
  wrap([$class: 'Xvnc', useXauthority: true]) {
    sh '''
      mvn \
      clean \
      verify \
      -B \
      $MAVEN_PROFILES \
      -Dmaven.repo.local=$WORKSPACE/.m2/repository \
      -Dorg.eclipse.justj.p2.manager.build.url=$JOB_URL \
      -Dbuild.type=$BUILD_TYPE \
      -Dgit.commit=$GIT_COMMIT \
      -Dgit.commit.${CLONE_URL}/commit/ \
      -Dorg.eclipse.storage.user=genie.datatools \
      -Dorg.eclipse.justj.p2.manager.target=datatools
    '''
  }
}
