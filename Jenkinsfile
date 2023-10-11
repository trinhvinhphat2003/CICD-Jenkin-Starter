pipeline {
    agent any
    tools { 
        maven 'my-maven' 
    }
    environment {
        MYSQL_ROOT_LOGIN = credentials('mysql')
    }
    stages {
        stage('Build with Maven') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
            }
        }
        stage('Packaging/Pushing imagae') {

            steps {
                withDockerRegistry(credentialsId: 'dockerhub', url: '') {
                    sh 'docker build -t trinhvinhphat2003/springboot .'
                    sh 'docker push trinhvinhphat2003/springboot'
                }
            }
        }
        stage('Deploy MySQL to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker image pull mysql:8.0'
                sh 'docker network create dev || echo "this network exists"'
                sh 'docker container stop phat-mysql || echo "this container does not exist" '
                sh 'echo y | docker container prune '
                sh 'docker volume rm phat-mysql-data || echo "no volume"'

                sh "docker run --name phat-mysql --rm --network dev -v phat-mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_LOGIN_PSW} -e MYSQL_DATABASE=db_example  -d mysql:8.0 "
                sh 'sleep 20'
                sh "docker exec -i khalid-mysql mysql --user=root --password=${MYSQL_ROOT_LOGIN_PSW} < script"
            }
        }
        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker image pull trinhvinhphat2003/springboot'
                sh 'docker container stop phat-springboot || echo "this container does not exist" '
                sh 'docker network create dev || echo "this network exists"'
                sh 'echo y | docker container prune '

                sh 'docker container run -d --rm --name phat-springboot -p 8081:8080 --network dev trinhvinhphat2003/springboot'
            }
        }
    }
}
