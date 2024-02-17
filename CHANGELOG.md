# Changelog

## [Unreleased]
### Added
- `circleci-templates` orb for common tasks

### Changed
- Specified all generated json config files except `serialization-config.json` to the `native-image` command
- chore(deps): update plugin io.micronaut.application to v3.7.10
- fix(deps): update dependency net.logstash.logback:logstash-logback-encoder to v7.4
- fix(deps): update dependency org.projectlombok:lombok to v1.18.30
- Made the `gradle-includes` location configurable
- chore(deps): update plugin org.sonarqube to v4.4.1.3373
- Configure Mend Bolt for GitHub
- fix(deps): update dependency ch.qos.logback:logback-classic to v1.4.14
- fix(deps): update dependency com.unboundid:unboundid-ldapsdk to v6.0.11
- fix(deps): update dependency org.codehaus.janino:janino to v3.1.11
- chore(deps): update dependency gradle to v8.5
- Upgraded micronaut to `3.10.3`
- fix(deps): update dependency net.bytebuddy:byte-buddy to v1.14.11
- fix(deps): update dependency org.codehaus.groovy:groovy-json to v3.0.20
- chore(deps): update plugin com.diffplug.spotless to v6.25.0
- chore(deps): update cimg/openjdk docker tag to v17.0.10
- fix(deps): update dependency org.codehaus.janino:janino to v3.1.12
- chore(deps): update dependency gradle to v8.6
- fix(deps): update dependency net.bytebuddy:byte-buddy to v1.14.12
- fix(deps): update dependency ch.qos.logback:logback-classic to v1.5.0

### Removed
- Dependency check plugin

## [2.0.0] - 2023-06-04
### Changed
- chore(deps): update plugin org.sonarqube to v4.2.0.3129
- chore(deps): update dependency gradle to v8
- [#24](https://github.com/devatherock/ldap-search-api/issues/24): Built a native binary using graalvm

## [1.0.0] - 2023-05-29
### Added
- [#27](https://github.com/devatherock/ldap-search-api/issues/27): Tests for built-in endpoints like `/health`

### Changed
- fix(deps): update dependency org.projectlombok:lombok to v1.18.28
- chore(deps): update plugin com.diffplug.spotless to v6.19.0
- chore(deps): update plugin org.sonarqube to v4.1.0.3113
- fix(deps): update dependency net.logstash.logback:logstash-logback-encoder to v7.3
- [#30](https://github.com/devatherock/ldap-search-api/issues/30): Updated dockerhub readme in CI pipeline
- [#32](https://github.com/devatherock/ldap-search-api/issues/32): Built a multi-arch docker image
- Used a multi-arch base image
- [#39](https://github.com/devatherock/ldap-search-api/issues/39]: Limited heap size in integration tests, to fix the out of memory error

## [0.5.0] - 2023-05-24
### Added
- Renovate config
- [#5](https://github.com/devatherock/ldap-search-api/issues/5): Query parameter to limit number of results
- [#7](https://github.com/devatherock/ldap-search-api/issues/7): Query parameter to specify the attributes to be returned in the result

## [0.4.0] - 2023-05-22
### Added
- [#2](https://github.com/devatherock/ldap-search-api/issues/2): Unit tests
- [#9](https://github.com/devatherock/ldap-search-api/issues/9): Integration tests
- Dependency check plugin
- Codenarc plugin

### Changed
- Upgraded micronaut to `3.9.1`
- Upgraded gradle to `7.6.1` and Java to 17
- Upgraded spotless to `6.18.0`
- Upgraded lombok to `1.18.26`

## [0.3.0] - 2021-08-28
### Added
- Spotless gradle plugin to format code
- [#10](https://github.com/devatherock/ldap-search-api/issues/10): Support for JSON logs

### Changed
- Upgraded micronaut to `2.4.2`

### Removed
- `jcenter` maven repo
- Custom environment variables with `LOGGING_LEVEL` prefix and updated documentation to use environment variables 
with `LOGGER_LEVELS` prefix supported out of the box by micronaut

## [0.2.0] - 2020-12-20
### Added
- [#4](https://github.com/devatherock/ldap-search-api/issues/4): Documented `jackson.serialization.indent-output` property that enables pretty JSON
- Customized the pretty printer to indent arrays

## [0.1.0] - 2020-12-20
### Added
- Initial version. REST endpoint to query a LDAP server

### Changed
- The response to a `List` of maps from a `Map`