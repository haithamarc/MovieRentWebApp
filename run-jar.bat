docker network create ftse
docker build -t ftse/customer-db -f ./dvd-rental-customer/dockerfiles/Dockerfile.db ./dvd-rental-customer
docker build -t ftse/film-db -f ./dvd-rental-film/dockerfiles/Dockerfile.db ./dvd-rental-film
docker build -t ftse/store-db -f ./dvd-rental-store/dockerfiles/Dockerfile.db ./dvd-rental-store
docker run --name customer-db --network ftse -dp 54323:5432 ftse/customer-db
docker run --name film-db --network ftse -dp 54321:5432 ftse/film-db
docker run --name store-db --network ftse -dp 54322:5432 ftse/store-db
timeout /t 5
docker exec -u postgres customer-db psql -qf /tmp/dvd-rental-customer.sql
docker exec -u postgres film-db psql -qf /tmp/dvd-rental-film.sql
docker exec -u postgres store-db psql -qf /tmp/dvd-rental-store.sql
cd ./dvd-rental-customer
call mvn -f pom-bootable.xml clean package -D"urls"=urlsDocker -D"datasource"=datasourceDocker
cd ../dvd-rental-film
call mvn -f pom-bootable.xml clean package -D"urls"=urlsDocker -D"datasource"=datasourceDocker
cd ../dvd-rental-store
call mvn -f pom-bootable.xml clean package -D"urls"=urlsDocker -D"datasource"=datasourceDocker
cd ..
docker build -t ftse/customer-jar  -f ./dvd-rental-customer/dockerfiles/Dockerfile.bootable ./dvd-rental-customer
docker build -t ftse/film-jar  -f ./dvd-rental-film/dockerfiles/Dockerfile.bootable ./dvd-rental-film
docker build -t ftse/store-jar  -f ./dvd-rental-store/dockerfiles/Dockerfile.bootable ./dvd-rental-store
docker run --name customer-jar --network ftse -dp 8083:8080 ftse/customer-jar
docker run --name film-jar --network ftse -dp 8081:8080 ftse/film-jar
docker run --name store-jar --network ftse -dp 8082:8080 ftse/store-jar