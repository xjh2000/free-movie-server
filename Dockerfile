FROM eclipse-temurin:17-jre-focal as builder
WORKDIR application
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

################################


FROM eclipse-temurin:17-jre-focal
MAINTAINER xjh <934622645@qq.com>
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

# JVM_XMS and JVM_XMX configs deprecated for removal in halov1.4.4
ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

ENTRYPOINT java  org.springframework.boot.loader.JarLauncher