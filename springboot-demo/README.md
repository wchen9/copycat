# Manual

## Build Docker Image
1. Run `mvn package` before build docker image;
2. Build docker image with command:
```shell
sudo docker build --build-arg JAR_FILE=target/*.jar -t springboot-demo:0.0.1 .
```
3. Run docker image with port mapping:
```shell
docker run --name springboot-demo -p 1234:1234 springboot-demo:0.0.1
```