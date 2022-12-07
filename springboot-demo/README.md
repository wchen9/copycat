# Manual

## Build Docker Image
1. Run `mvn package` before build docker image;
2. Build docker image with command:
```shell
sudo docker build --build-arg JAR_FILE=target/*.jar -t springboot-demo:0.0.1 .
```