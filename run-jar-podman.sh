podman network create ftse
podman build -t ftse/customer-db -f ./dvd-rental-customer/dockerfiles/Dockerfile.db ./dvd-rental-customer
podman build -t ftse/film-db -f ./dvd-rental-film/dockerfiles/Dockerfile.db ./dvd-rental-film
podman build -t ftse/store-db -f ./dvd-rental-store/dockerfiles/Dockerfile.db ./dvd-rental-store
podman run --name customer-db --network ftse -dp 54323:5432 ftse/customer-db
podman run --name film-db --network ftse -dp 54321:5432 ftse/film-db
podman run --name store-db --network ftse -dp 54322:5432 ftse/store-db
sleep 5
podman exec -u postgres customer-db psql -qf /tmp/dvd-rental-customer.sql
podman exec -u postgres film-db psql -qf /tmp/dvd-rental-film.sql
podman exec -u postgres store-db psql -qf /tmp/dvd-rental-store.sql
cd ./dvd-rental-customer
mvn -f pom-bootable.xml clean package -D"datasource"=datasourceDocker
cd ../dvd-rental-film
mvn -f pom-bootable.xml clean package -D"datasource"=datasourceDocker
cd ../dvd-rental-store
mvn -f pom-bootable.xml clean package -D"datasource"=datasourceDocker
cd ..
podman build -t ftse/customer-jar  -f ./dvd-rental-customer/dockerfiles/Dockerfile.bootable ./dvd-rental-customer
podman build -t ftse/film-jar  -f ./dvd-rental-film/dockerfiles/Dockerfile.bootable ./dvd-rental-film
podman build -t ftse/store-jar  -f ./dvd-rental-store/dockerfiles/Dockerfile.bootable ./dvd-rental-store
podman run --name customer-jar --network ftse -dp 8083:8080 ftse/customer-jar
podman run --name film-jar --network ftse -dp 8081:8080 ftse/film-jar
podman run --name store-jar --network ftse -dp 8082:8080 ftse/store-jar