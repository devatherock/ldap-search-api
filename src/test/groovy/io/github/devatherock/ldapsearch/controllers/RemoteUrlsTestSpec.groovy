package io.github.devatherock.ldapsearch.controllers

import io.micronaut.test.extensions.spock.annotation.MicronautTest

/**
 * Unit test that calls remote endpoints
 */
@MicronautTest(propertySources = ['classpath:application-test.yml', 'classpath:application-remote.yml'])
class RemoteUrlsTestSpec extends RemoteUrlsSpec {
}
