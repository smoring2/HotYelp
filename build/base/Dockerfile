FROM node:16
MAINTAINER <Xi Shen> smoring1995@gmail.com

RUN yarn global add serve

RUN apt-get update \
    && apt-get install -y gnupg software-properties-common \
    && wget -qO - https://apt.corretto.aws/corretto.key | gpg --dearmor -o /usr/share/keyrings/corretto-keyring.gpg \
    && echo "deb [signed-by=/usr/share/keyrings/corretto-keyring.gpg] https://apt.corretto.aws stable main" > /etc/apt/sources.list.d/corretto.list \
    && apt-get update \
    && apt-get install -y java-1.8.0-amazon-corretto-jdk


