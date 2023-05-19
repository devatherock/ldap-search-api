package io.github.devatherock.ldapsearch.controllers

import javax.inject.Inject

import groovy.json.JsonSlurper

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import spock.lang.Specification

/**
 * Test that calls remote endpoints. Needed for proper netty SSL reflection config
 */
abstract class RemoteUrlsSpec extends Specification {

    @Inject
    @Client('${test.server.url}')
    HttpClient httpClient

    JsonSlurper slurper = new JsonSlurper()

    void 'test search - get specific user'() {
        when:
        String response = httpClient.toBlocking().retrieve(
                HttpRequest.GET(UriBuilder.of('/search')
                        .queryParam('filter', 'uid=test').build())
        )

        then:
        def json = slurper.parseText(response)
        json.size() == 1
        json[0]['givenName'] == 'Test'
        json[0]['sn'] == 'User'
        json[0]['objectClass'].containsAll(['top', 'person', 'organizationalPerson', 'inetOrgPerson'])
        json[0]['uid'] == 'test'
        json[0]['cn'] == 'Test User'
    }
}
