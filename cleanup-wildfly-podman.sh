podman rm -f customer-db film-db store-db customer-wildfly film-wildfly store-wildfly
podman rmi ftse/customer-db ftse/film-db ftse/store-db ftse/customer-wildfly ftse/film-wildfly ftse/store-wildfly
podman network rm ftse