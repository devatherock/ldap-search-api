# Changelog

## [Unreleased]
### Changed
- fix(deps): update dependency net.bytebuddy:byte-buddy to v1.15.11
- fix(deps): update dependency ch.qos.logback:logback-classic to v1.5.15
- chore(deps): update dependency gradle to v8.12
- fix(deps): update dependency ch.qos.logback:logback-classic to v1.5.16
- chore(deps): update alpine docker tag to v3.21.2
- chore(deps): update plugin com.diffplug.spotless to v7
- chore(deps): update plugin com.diffplug.spotless to v7.0.2
- chore(deps): update dependency gradle to v8.12.1
- chore(deps): update plugin io.micronaut.application to v4.4.5
- fix(deps): update dependency org.apache.groovy:groovy-json to v4.0.25
- fix(deps): update dependency net.bytebuddy:byte-buddy to v1.17.0
- chore(deps): update alpine docker tag to v3.21.3
- fix(deps): update dependency net.bytebuddy:byte-buddy to v1.17.1
- fix(deps): update dependency ch.qos.logback:logback-classic to v1.5.17
- fix(deps): update dependency org.apache.groovy:groovy-json to v4.0.26
- chore(deps): update dependency gradle to v8.13
- fix(deps): update dependency net.bytebuddy:byte-buddy to v1.17.2

## [2.4.0] - 2024-12-12
### Added
- [#164](https://github.com/devatherock/ldap-search-api/issues/164): Support for anonymous access

### Changed
- fix(deps): update dependency ch.qos.logback:logback-classic to v1.5.12
- chore(deps): update plugin io.micronaut.application to v4.4.4
- fix(deps): update dependency net.bytebuddy:byte-buddy to v1.15.10
- fix(deps): update dependency org.apache.groovy:groovy-json to v4.0.24
- fix(deps): update dependency org.projectlombok:lombok to v1.18.36
- chore(deps): update dependency gradle to v8.11.1
- chore(deps): update plugin org.sonarqube to v6.0.1.5171
- fix(deps): update dependency com.unboundid:unboundid-ldapsdk to v7.0.2
- chore(deps): update alpine docker tag to v3.21.0

## [2.3.0] - 2024-09-29
### Changed
- chore(deps): update dependency gradle to v8.10.2
- Upgraded micronaut to `4.6.2`
- Upgraded to Java 21
- fix(deps): update dependency net.bytebuddy:byte-buddy to v1.15.3
- Built the native image using gradle
- fix(deps): update dependency org.apache.groovy:groovy-json to v4.0.23

## [2.2.0] - 2024-09-26
### Changed
- fix(deps): update dependency org.objenesis:objenesis to v3.4
- fix(deps): update dependency com.unboundid:unboundid-ldapsdk to v7.0.1
- fix(deps): update dependency org.projectlombok:lombok to v1.18.34
- fix(deps): update dependency org.codehaus.groovy:groovy-json to v3.0.22
- chore(deps): update plugin org.sonarqube to v5.1.0.4882
- fix(deps): update dependency net.logstash.logback:logstash-logback-encoder to v8
- fix(deps): update dependency ch.qos.logback:logback-classic to v1.5.8
- chore(deps): update dependency gradle to v8.10.1
- fix(deps): update dependency net.bytebuddy:byte-buddy to v1.15.1
- Upgraded graal version from 22.1.0 to 23

## [2.1.0] - 2024-05-04
### Added
- `circleci-templates` orb for common tasks

### Changed
- Specified all generated json config files except `serialization-config.json` to the `native-image` command
- chore(deps): update plugin io.micronaut.application to v3.7.10
- fix(deps): update dependency net.logstash.logback:logstash-logback-encoder to v7.4
- Made the `gradle-includes` location configurable
- Configure Mend Bolt for GitHub
- Upgraded micronaut to `3.10.3`
- chore(deps): update plugin com.diffplug.spotless to v6.25.0
- fix(deps): update dependency org.codehaus.janino:janino to v3.1.12
- fix(deps): update dependency org.codehaus.groovy:groovy-json to v3.0.21
- fix(deps): update dependency com.unboundid:unboundid-ldapsdk to v7
- fix(deps): update dependency org.projectlombok:lombok to v1.18.32
- chore(deps): update dependency gradle to v8.7
- chore(deps): update plugin org.sonarqube to v5
- chore(deps): update cimg/openjdk docker tag to v17.0.11
- fix(deps): update dependency ch.qos.logback:logback-classic to v1.5.6
- fix(deps): update dependency net.bytebuddy:byte-buddy to v1.14.14
- Combined the amd64 and arm64 images into a single multi-arch image

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