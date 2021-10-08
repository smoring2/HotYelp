echo "current dir: $(pwd)"
mvn package

cd frontend/goyelp-frontend || exit
npm run build
cd ../..

docker build -f build/hotyelp/Dockerfile -t us-docker.pkg.dev/strong-land-326003/docker-registry/hotyelp .
docker push us-docker.pkg.dev/strong-land-326003/docker-registry/hotyelp