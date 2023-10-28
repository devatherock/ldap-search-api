package io.github.devatherock.ldapsearch.controllers

import groovy.json.JsonSlurper

import com.unboundid.ldap.listener.InMemoryDirectoryServer
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig
import com.unboundid.ldap.listener.InMemoryListenerConfig
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.uri.UriBuilder
import jakarta.inject.Inject
import spock.lang.Shared
import spock.lang.Specification

/**
 * Test class for {@link LdapSearchController}
 */
abstract class LdapSearchControllerSpec extends Specification {

    @Shared
    InMemoryDirectoryServer directoryServer

    @Inject
    @Client('${test.server.url}')
    HttpClient httpClient

    JsonSlurper slurper = new JsonSlurper()

    void setupSpec() {
        InMemoryDirectoryServerConfig config =
                new InMemoryDirectoryServerConfig('dc=example,dc=com')
        config.addAdditionalBindCredentials('cn=Directory Manager', 'testpwd')
        config.setListenerConfigs(
                new InMemoryListenerConfig('testListener', null, 33389,
                        null, null, null))

        directoryServer = new InMemoryDirectoryServer(config)
        directoryServer.importFromLDIF(true,
                new File(LdapSearchControllerSpec.class.classLoader.getResource('test.ldif').toURI()))
        directoryServer.startListening()
    }

    void cleanupSpec() {
        directoryServer.shutDown(true)
    }

    void 'test search - get all users'() {
        when:
        String response = httpClient.toBlocking().retrieve(
                HttpRequest.GET(UriBuilder.of('/search')
                        .queryParam('filter', 'uid=*').build())
        )

        then:
        def json = slurper.parseText(response)
        json.size() == 3
        verifyResult(json, 'Test User', 'User', 'testUID', 'abcd')
        verifyResult(json, 'Santa Claus', 'Claus', 'sclaus', 'abcde')
        verifyResult(json, 'John Steinbeck', 'Steinbeck', 'jsteinbeck', 'abcdef')
    }

    void 'test search - get all users with limit'() {
        when:
        String response = httpClient.toBlocking().retrieve(
                HttpRequest.GET(UriBuilder.of('/search')
                        .queryParam('filter', 'uid=*')
                        .queryParam('limit', '1')
                        .build())
        )

        then:
        def json = slurper.parseText(response)
        json.size() == 1
        ['Test User', 'Santa Claus', 'John Steinbeck'].contains(json[0]['cn'])
    }

    void 'test search - get specific user'() {
        when:
        String response = httpClient.toBlocking().retrieve(
                HttpRequest.GET(UriBuilder.of('/search')
                        .queryParam('filter', 'uid=sclaus').build())
        )

        then:
        response == getExpectedJson()
        def json = slurper.parseText(response)
        json.size() == 1
        verifyResult(json, 'Santa Claus', 'Claus', 'sclaus', 'abcde')
    }

    void 'test search - get specific user and specific attributes'() {
        when:
        String response = httpClient.toBlocking().retrieve(
                HttpRequest.GET(UriBuilder.of('/search')
                        .queryParam('filter', 'uid=sclaus')
                        .queryParam('attributes', 'cn,sn')
                        .build())
        )

        then:
        def json = slurper.parseText(response)
        json.size() == 1
        json[0].size() == 2
        json[0]['cn'] == 'Santa Claus'
        json[0]['sn'] == 'Claus'
    }

    void 'test search - user not found'() {
        when:
        httpClient.toBlocking().retrieve(
                HttpRequest.GET(UriBuilder.of('/search')
                        .queryParam('filter', 'uid=dummy').build())
        )

        then:
        HttpClientResponseException exception = thrown()
        exception.status.code == 404
        def json = slurper.parseText(exception.response.body())
        json.size() == 0
    }

    void 'test search - known base_dn specified'() {
        when:
        String response = httpClient.toBlocking().retrieve(
                HttpRequest.GET(UriBuilder.of('/search')
                        .queryParam('filter', 'uid=sclaus')
                        .queryParam('base_dn', baseDn)
                        .build())
        )

        then:
        def json = slurper.parseText(response)
        json.size() == 1
        verifyResult(json, 'Santa Claus', 'Claus', 'sclaus', 'abcde')

        where:
        baseDn << [
                'dc=example,dc=com',
                'ou=Users,dc=example,dc=com',
        ]
    }

    void 'test search - unknown base_dn specified'() {
        when:
        httpClient.toBlocking().retrieve(
                HttpRequest.GET(UriBuilder.of('/search')
                        .queryParam('filter', 'uid=sclaus')
                        .queryParam('base_dn', 'dc=dummy,dc=com')
                        .build())
        )

        then:
        HttpClientResponseException exception = thrown()
        exception.status.code == 404
        def json = slurper.parseText(exception.response.body())
        json.size() == 0
    }

    protected void verifyResult(List results, String commonName, String surname, String userId, String password) {
        def result = results.find { it['cn'] == commonName }

        assert result != null
        assert result['objectClass'].containsAll(['top', 'person', 'organizationalPerson', 'inetOrgPerson'])
        assert result['sn'] == surname
        assert result['uid'] == userId
        assert result['userPassword'] == new String(Base64.encoder.encode(password.bytes))
    }

    abstract protected String getExpectedJson()
}
