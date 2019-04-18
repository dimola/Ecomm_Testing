node{
    stage (‘SCM checkout’){
        git “https://github.com/dimola/Ecomm_Testing”
    }
    stage("Execute tests"){
        echo "Executing tests"
        sh 'mvn test'
        echo "End of test execution"
    }

    stage("Create report"){
        echo "Create report"
    }
}