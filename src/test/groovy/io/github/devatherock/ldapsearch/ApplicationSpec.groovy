package io.github.devatherock.ldapsearch

import javax.inject.Inject

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

@MicronautTest(propertySources = 'classpath:application-test.yml')
class ApplicationSpec extends Specification {

    @Inject
    EmbeddedApplication<?> application

    void 'test it works'() {
        expect:
        application.running
    }

}
