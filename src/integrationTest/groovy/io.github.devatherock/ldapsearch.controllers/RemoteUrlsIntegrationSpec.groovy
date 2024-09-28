package io.github.devatherock.ldapsearch.controllers

import java.nio.file.Files
import java.nio.file.Paths

import io.micronaut.test.extensions.spock.annotation.MicronautTest

/**
 * Integration test that calls remote endpoints
 */
@MicronautTest(propertySources = 'classpath:application-integration.yml', startApplication = false)
class RemoteUrlsIntegrationSpec extends RemoteUrlsSpec {

    void 'test log output'() {
        expect:
        Files.readString(Paths.get('logs-intg-remote.txt')).contains('Startup completed')
    }
}
