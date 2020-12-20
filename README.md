[![CircleCI](https://circleci.com/gh/devatherock/ldap-search-api.svg?style=svg)](https://circleci.com/gh/devatherock/ldap-search-api)
[![Version](https://img.shields.io/docker/v/devatherock/ldap-search-api?sort=semver)](https://hub.docker.com/r/devatherock/ldap-search-api/)
[![Coverage Status](https://coveralls.io/repos/github/devatherock/ldap-search-api/badge.svg?branch=master)](https://coveralls.io/github/devatherock/ldap-search-api?branch=master)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=ldap-search-api&metric=alert_status)](https://sonarcloud.io/component_measures?id=ldap-search-api&metric=alert_status&view=list)
[![Docker Pulls](https://img.shields.io/docker/pulls/devatherock/ldap-search-api.svg)](https://hub.docker.com/r/devatherock/ldap-search-api/)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=ldap-search-api&metric=ncloc)](https://sonarcloud.io/component_measures?id=ldap-search-api&metric=ncloc)
[![Docker Image Size](https://img.shields.io/docker/image-size/devatherock/ldap-search-api.svg?sort=date)](https://hub.docker.com/r/devatherock/ldap-search-api/)
# ldap-search-api
Simple REST API to query a LDAP server

## Usage
### Sample command
```
docker run --rm \
  -p 8080:8080 \
  -e LDAP_HOST=ldaps://ldap.jumpcloud.com:636 \
  -e LDAP_USERNAME=devatherock \
  -e LDAP_PASSWORD=dummy \
  devatherock/ldap-search-api:latest
```

### Configurable properties

| Environment Variable Name             | YAML Variable Name    |   Required   |   Default        |   Description                                                  |
|---------------------------------------|-----------------------|--------------|------------------|----------------------------------------------------------------|
| LDAP_HOST                             | ldap.host             |    true      |   (None)         |   The full host name of the LDAP server. Example: `ldaps://ldap.jumpcloud.com:636` |
| LDAP_USERNAME                         | ldap.username         |    true      |   (None)         |   The LDAP bind username. Could be a simple username like `devatherock` or a DN like `uid=devatherock,ou=Users,dc=jumpcloud,dc=com` depending on how the LDAP server is configured  |
| LDAP_PASSWORD                         | ldap.password         |    true      |   (None)         |   The LDAP bind password                                       |
| LDAP_BASE_DN                          | ldap.base-dn          |    false     |   (None)         |   The default base DN to search against                        |
| LDAP_BINARY_ATTRIBUTES                | ldap.binary-attributes  |    false   |   (None)         |   Attributes in the search result that have binary values      |
| LDAP_READ_TIMEOUT_MILLIS              | ldap.read-timeout-millis  |  false   |   10000          |   Read timeout for the search, in milliseconds. Defaults to 10 seconds  |
| LDAP_CONNECTION_POOL_ENABLED          | ldap.connection-pool.enabled |  false  |   true         |   Indicates if a connection pool should be used                |
| LDAP_CONNECTION_POOL_CORE_SIZE        | ldap.connection-pool.core-size |  false  |   8          |   Initial size of the connection pool                          |
| LDAP_CONNECTION_POOL_MAX_SIZE         | ldap.connection-pool.max-size |  false   |   8          |   Maximum size of the connection pool                          |
| LDAP_CONNECTION_POOL_TIME_TO_LIVE_MILLIS  | ldap.connection-pool.timeToLiveMillis |  false   |   1,800,000  |   The total time a connection in the pool will be kept open, in milliseconds. Defaults to 30 minutes  |
| LOGGING_LEVEL_ROOT                    | (None)                |    false     |   INFO           |   [SLF4J](http://www.slf4j.org/api/org/apache/commons/logging/Log.html) log level, for all(framework and custom) code  |
| LOGGING_LEVEL_IO_GITHUB_DEVATHEROCK   | (None)                |    false     |   INFO           |   [SLF4J](http://www.slf4j.org/api/org/apache/commons/logging/Log.html) log level, for custom code  |
| MICRONAUT_SERVER_PORT                 | micronaut.server.port |    false     |   8080           |   Port in which the app listens on                              |
| MICRONAUT_CONFIG_FILES                | (None)                |    false     |   (None)         |   Path to YAML config files. The YAML files can be used to specify complex, object and array properties  | 
| JAVA_OPTS                             | (None)                |    false     |   (None)         |   Additional JVM arguments to be passed to the container's java process  |

### Endpoints
- `/search?filter=<an ldap query>` - Searches LDAP under the given base DN with the supplied filter criteria

## Troubleshooting
### Enabling debug logs
- Set the environment variable `LOGGING_LEVEL_ROOT` to `DEBUG` to enable all debug logs - custom and framework
- Set the environment variable `LOGGING_LEVEL_IO_GITHUB_DEVATHEROCK` to `DEBUG` to enable debug logs only in custom code
- For fine-grained logging control, supply a custom [logback.xml](http://logback.qos.ch/manual/configuration.html) file
and set the environment variable `JAVA_OPTS` to `-Dlogback.configurationFile=/path/to/custom/logback.xml`