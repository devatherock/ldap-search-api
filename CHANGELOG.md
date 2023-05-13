# Changelog

## [Unreleased]
### Added
- [#2](https://github.com/devatherock/ldap-search-api/issues/2): Unit tests
- [#9](https://github.com/devatherock/ldap-search-api/issues/9): Integration tests
- Dependency check and codenarc gradle plugins

### Changed
- Upgraded micronaut to `3.9.1`
- Upgraded gradle to `7.6.1` and Java to 17
- Upgraded spotless to `6.18.0`

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