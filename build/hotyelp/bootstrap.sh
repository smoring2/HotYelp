echo "current dir: $(pwd)"

java -jar GoYelp-0.0.1-SNAPSHOT.jar &
cd goyelp-frontend || exit
serve -s build -l 3000

sleep infinity