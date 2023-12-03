docker_tag=latest

clean:
	rm -rf build
check:
	./gradlew check -Dgraalvm=true $(additional_gradle_args)
integration-test:
	DOCKER_TAG=$(docker_tag) docker-compose up &
	./gradlew integrationTest --tests '*ControllerIntegrationSpec*'
	docker-compose down
remote-integration-test:
	DOCKER_TAG=$(docker_tag) docker-compose -f docker-compose-remote.yml up &
	./gradlew integrationTest --tests '*RemoteUrlsIntegrationSpec*'
	docker-compose down
build-all:
	./gradlew build	-Dgraalvm=true
fast-build:
	./gradlew build shadowJar -Dgraalvm=true -x assemble -x dependencyCheckAggregate
docker-build:
	docker build -t devatherock/ldap-search-api:$(docker_tag) .