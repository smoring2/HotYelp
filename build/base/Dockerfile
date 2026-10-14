FROM node:16
MAINTAINER <Xi Shen> smoring1995@gmail.com

RUN yarn global add serve

RUN wget -qO - https://apt.corretto.aws/corretto.key | apt-key add - \
    && apt-get update \
    && apt-get install -y software-properties-common \
    && add-apt-repository --yes 'deb https://apt.corretto.aws stable main' \
    && apt-get update \
    && apt-get install -y java-1.8.0-amazon-corretto-jdk


