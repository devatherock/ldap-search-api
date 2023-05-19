DOCKER_TAG=latest

clean:
	./gradlew clean
integration-test:
	docker-compose up &
	./gradlew integrationTest --tests '*ControllerIntegrationSpec*'
	docker-compose down
remote-integration-test:
	docker-compose -f docker-compose-remote.yml up &
	./gradlew integrationTest --tests '*RemoteUrlsIntegrationSpec*'
	docker-compose down
docker-build:
	./gradlew clean build -Dgraalvm=true
	docker build -t devatherock/ldap-search-api:$(DOCKER_TAG) .