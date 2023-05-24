package io.github.devatherock.ldapsearch.controllers

import javax.inject.Inject

import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Test for additional endpoints like {@code /health}
 */
abstract class AdditionalEndpointsSpec extends Specification {

    @Inject
    @Client('${test.server.url}')
    HttpClient httpClient

    @Unroll
    void 'test endpoint - #endpoint'() {
        when:
        HttpResponse response = httpClient.toBlocking().exchange(endpoint)

        then:
        response.status.code == 200

        where:
        endpoint << [
                '/health',
                '/metrics',
                '/swagger-ui',
                '/swagger/ldap-search-api-v1.yml'
        ]
    }
}
