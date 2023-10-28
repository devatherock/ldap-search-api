package io.github.devatherock.ldapsearch.controllers

import java.nio.file.Files
import java.nio.file.Paths

import io.micronaut.test.extensions.spock.annotation.MicronautTest

/**
 * Integration test for additional endpoints
 */
@MicronautTest(propertySources = 'classpath:application-integration.yml', startApplication = false)
class AdditionalControllerIntegrationSpec extends AdditionalControllerSpec {

    void 'test log format'() {
        expect:
        Files.readString(Paths.get('logs-intg.txt')).contains('"message":"Startup completed')
    }
}
