docker rm -f customer-db film-db store-db customer-jar film-jar store-jar
docker rmi ftse/customer-db ftse/film-db ftse/store-db ftse/customer-jar ftse/film-jar ftse/store-jar
docker network rm ftse