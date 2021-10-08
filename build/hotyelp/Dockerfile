FROM us-docker.pkg.dev/strong-land-326003/docker-registry/base
MAINTAINER <Xi Shen> smoring1995@gmail.com


WORKDIR /app
COPY target/GoYelp-0.0.1-SNAPSHOT.jar .

ADD frontend/goyelp-frontend goyelp-frontend/

COPY build/hotyelp/bootstrap.sh .

ENTRYPOINT ["sh", "bootstrap.sh"]

