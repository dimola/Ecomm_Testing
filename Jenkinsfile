node{
    stage ("SCM checkout"){
        echo 'Pulling from git repository'
        git 'https://github.com/dimola/Ecomm_Testing'
    }
    stage("Build"){
        echo 'Cleaning the workspace project folder'
        sh 'mvn clean'
        echo 'Compiling the java files'
        sh 'mvn compile'
    }
    stage("Execute tests"){
        echo "Executing tests"
        withMaven(jdk: 'Java_Home', maven: 'Maven_Home', tempBinDir: '') {
            sh 'mvn test'
        }
        echo "End of test execution"
    }
    stage("Create report"){
        echo "Create report TBD"
    }
}