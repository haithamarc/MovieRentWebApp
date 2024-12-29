docker rm -f customer-db film-db store-db customer-wildfly film-wildfly store-wildfly
docker rmi ftse/customer-db ftse/film-db ftse/store-db ftse/customer-wildfly ftse/film-wildfly ftse/store-wildfly
docker network rm ftse