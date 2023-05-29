package io.github.devatherock.ldapsearch.controllers

import io.micronaut.test.extensions.spock.annotation.MicronautTest

/**
 * Integration test for additional endpoints
 */
@MicronautTest(propertySources = 'classpath:application-integration.yml', startApplication = false)
class AdditionalControllerIntegrationSpec extends AdditionalControllerSpec {
}
