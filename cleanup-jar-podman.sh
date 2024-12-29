podman rm -f customer-db film-db store-db customer-jar film-jar store-jar
podman rmi ftse/customer-db ftse/film-db ftse/store-db ftse/customer-jar ftse/film-jar ftse/store-jar
podman network rm ftse