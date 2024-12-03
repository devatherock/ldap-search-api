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

| Environment Variable Name                | YAML Variable Name                    | Required | Default   | Description                                                                                                                                                                      |
|------------------------------------------|---------------------------------------|----------|-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| LDAP_HOST                                | ldap.host                             | true     | (None)    | The full host name of the LDAP server. Example: `ldaps://ldap.jumpcloud.com:636`                                                                                                 |
| LDAP_USERNAME                            | ldap.username                         | false    | (None)    | The LDAP bind username. Could be a simple username like `devatherock` or a DN like `uid=devatherock,ou=Users,dc=jumpcloud,dc=com` depending on how the LDAP server is configured |
| LDAP_PASSWORD                            | ldap.password                         | false    | (None)    | The LDAP bind password                                                                                                                                                           |
| LDAP_AUTH_TYPE                           | ldap.auth-type                        | false    | simple    | Value for the `java.naming.security.authentication` property. Supported values are `none`, `simple`. Defaults to `simple`                                                        |
| LDAP_BASE_DN                             | ldap.base-dn                          | false    | (None)    | The default base DN to search against                                                                                                                                            |
| LDAP_BINARY_ATTRIBUTES                   | ldap.binary-attributes                | false    | (None)    | Attributes in the search result that have binary values                                                                                                                          |
| LDAP_READ_TIMEOUT_MILLIS                 | ldap.read-timeout-millis              | false    | 10000     | Read timeout for the search, in milliseconds. Defaults to 10 seconds                                                                                                             |
| LDAP_CONNECTION_POOL_ENABLED             | ldap.connection-pool.enabled          | false    | true      | Indicates if a connection pool should be used                                                                                                                                    |
| LDAP_CONNECTION_POOL_CORE_SIZE           | ldap.connection-pool.core-size        | false    | 8         | Initial size of the connection pool                                                                                                                                              |
| LDAP_CONNECTION_POOL_MAX_SIZE            | ldap.connection-pool.max-size         | false    | 8         | Maximum size of the connection pool                                                                                                                                              |
| LDAP_CONNECTION_POOL_TIME_TO_LIVE_MILLIS | ldap.connection-pool.timeToLiveMillis | false    | 1,800,000 | The total time a connection in the pool will be kept open, in milliseconds. Defaults to 30 minutes                                                                               |
| LOGGER_LEVELS_ROOT                       | (None)                                | false    | INFO      | [SLF4J](http://www.slf4j.org/api/org/apache/commons/logging/Log.html) log level, for all(framework and custom) code                                                              |
| LOGGER_LEVELS_IO_GITHUB_DEVATHEROCK      | (None)                                | false    | INFO      | [SLF4J](http://www.slf4j.org/api/org/apache/commons/logging/Log.html) log level, for custom code                                                                                 |
| MICRONAUT_SERVER_PORT                    | micronaut.server.port                 | false    | 8080      | Port in which the app listens on                                                                                                                                                 |
| MICRONAUT_CONFIG_FILES                   | (None)                                | false    | (None)    | Path to YAML config files. The YAML files can be used to specify complex, object and array properties                                                                            |
| JACKSON_SERIALIZATION_INDENT_OUTPUT      | jackson.serialization.indent-output   | false    | (None)    | Set to `true` to enable JSON pretty-print of response                                                                                                                            |
| LOGBACK_CONFIGURATION_FILE               | (None)                                | false    | (None)    | Path to logback configuration file                                                                                                                                               |

### API spec
When the app is running, detailed API documentation can be accessed at `{host}/swagger-ui` or `{host}/swagger/ldap-search-api-{version}.yml`. The available endpoints are listed below for reference:

- `/search?filter=<an ldap query>&limit=<number of results>` - Searches LDAP under the given base DN with the supplied filter criteria

### Sample response
```json
[
    {
        "loginShell": "/bin/bash",
        "uid": "testdummy",
        "homeDirectory": "/home/test",
        "mail": "test.dummy@dummy.com",
        "uidNumber": "1234",
        "givenName": "Test",
        "objectClass": [
            "top",
            "person",
            "organizationalPerson",
            "inetOrgPerson",
            "shadowAccount",
            "posixAccount",
            "jumpcloudUser"
        ],
        "sn": "User",
        "gidNumber": "1234",
        "cn": "Test User"
    }
]
```

## Troubleshooting
### Enabling debug logs
- Set the environment variable `LOGGER_LEVELS_ROOT` to `DEBUG` to enable all debug logs - custom and framework
- Set the environment variable `LOGGER_LEVELS_IO_GITHUB_DEVATHEROCK` to `DEBUG` to enable debug logs only in custom code
- For fine-grained logging control, supply a custom [logback.xml](http://logback.qos.ch/manual/configuration.html) file
and set the environment variable `LOGBACK_CONFIGURATION_FILE` to `/path/to/custom/logback.xml`

### JSON logs

Refer [logstash-logback-encoder](https://github.com/logstash/logstash-logback-encoder) documentation to customize the field names and formats in the log. To output logs as JSON, set the environment variable `LOGBACK_CONFIGURATION_FILE` to `logback-json.xml`
