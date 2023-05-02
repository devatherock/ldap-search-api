DOCKER_TAG=latest

clean:
	./gradlew clean
integration-test:
	docker-compose up &
	./gradlew integrationTest
	docker-compose down
docker-build:
	./gradlew clean build -Dgraalvm=true
	docker build -t devatherock/ldap-search-api:$(DOCKER_TAG) .