docker_tag=latest

clean:
	rm -rf build
check:
	./gradlew check $(additional_gradle_args)
integration-test:
	rm -rf logs-intg.txt
	DOCKER_TAG=$(docker_tag) docker compose up --wait
	docker logs -f ldap-search-api-intg > logs-intg.txt &
	./gradlew integrationTest --tests '*ControllerIntegrationSpec*'
	docker-compose down
no-auth-integration-test:
	rm -rf logs-intg-no-auth.txt
	DOCKER_TAG=$(docker_tag) docker compose -f docker-compose-no-auth.yml up --wait
	docker logs -f ldap-search-api-intg-no-auth > logs-intg-no-auth.txt &
	./gradlew integrationTest --tests '*LdapSearchControllerNoAuthIntegrationSpec*'
	docker compose -f docker-compose-no-auth.yml down
remote-integration-test:
	rm -rf logs-intg-remote.txt
	DOCKER_TAG=$(docker_tag) docker compose -f docker-compose-remote.yml up --wait
	docker logs -f ldap-search-api-intg-remote > logs-intg-remote.txt &
	./gradlew integrationTest --tests '*RemoteUrlsIntegrationSpec*'
	docker compose -f docker-compose-remote.yml down
build-all:
	./gradlew build
fast-build:
	./gradlew build -x test
docker-build:
	./gradlew dockerBuildNative -Dnative.threads=2 -Dnative.xmx=3072m \
	    -Dnative.tag=$(docker_tag) -Dnative.arch=native -Dnative.mode=dev
