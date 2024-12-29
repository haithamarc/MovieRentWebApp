docker network create ftse
docker build -t ftse/customer-db -f ./dvd-rental-customer/dockerfiles/Dockerfile.db ./dvd-rental-customer
docker build -t ftse/film-db -f ./dvd-rental-film/dockerfiles/Dockerfile.db ./dvd-rental-film
docker build -t ftse/store-db -f ./dvd-rental-store/dockerfiles/Dockerfile.db ./dvd-rental-store
docker run --name customer-db --network ftse -dp 54323:5432 ftse/customer-db
docker run --name film-db --network ftse -dp 54321:5432 ftse/film-db
docker run --name store-db --network ftse -dp 54322:5432 ftse/store-db
sleep 5
docker exec -u postgres customer-db psql -qf /tmp/dvd-rental-customer.sql
docker exec -u postgres film-db psql -qf /tmp/dvd-rental-film.sql
docker exec -u postgres store-db psql -qf /tmp/dvd-rental-store.sql
cd ./dvd-rental-customer
mvn clean package -D"urls"=urlsDocker
cd ../dvd-rental-film
mvn clean package -D"urls"=urlsDocker
cd ../dvd-rental-store
mvn clean package -D"urls"=urlsDocker
cd ..
docker build -t ftse/customer-wildfly  -f ./dvd-rental-customer/dockerfiles/Dockerfile.wildfly ./dvd-rental-customer
docker build -t ftse/film-wildfly -f ./dvd-rental-film/dockerfiles/Dockerfile.wildfly ./dvd-rental-film
docker build -t ftse/store-wildfly -f ./dvd-rental-store/dockerfiles/Dockerfile.wildfly ./dvd-rental-store
docker run --name customer-wildfly --network ftse -dp 8083:8080 ftse/customer-wildfly
docker run --name film-wildfly --network ftse -dp 8081:8080 ftse/film-wildfly
docker run --name store-wildfly --network ftse -dp 8082:8080 ftse/store-wildfly