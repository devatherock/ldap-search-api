# Changelog

## [Unreleased]
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