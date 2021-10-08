FROM node:16
MAINTAINER <Xi Shen> smoring1995@gmail.com

RUN yarn global add serve

RUN wget -qO - https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | apt-key add - \
    && apt-get update \
    && apt-get install -y software-properties-common \
    && add-apt-repository --yes https://adoptopenjdk.jfrog.io/adoptopenjdk/deb/ \
    && apt-get update \
    && apt-get install -y adoptopenjdk-8-hotspot


