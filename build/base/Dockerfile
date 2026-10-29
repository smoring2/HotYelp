FROM eclipse-temurin:21-jdk AS jdk

FROM node:16
MAINTAINER <Xi Shen> smoring1995@gmail.com

RUN yarn global add serve

COPY --from=jdk /opt/java/openjdk /opt/java/openjdk
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"


