package io.github.devatherock.ldapsearch.controllers

import javax.inject.Inject

import groovy.json.JsonSlurper

import com.unboundid.ldap.listener.InMemoryDirectoryServer
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig
import com.unboundid.ldap.listener.InMemoryListenerConfig
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Shared
import spock.lang.Specification

@MicronautTest
class LdapSearchControllerSpec extends Specification {

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
                        .queryParam('filter', 'uid=*').build()))

        then:
        def json = slurper.parseText(response)
        json.size() == 1
        json[0]['objectClass'].containsAll(['top', 'inetOrgPerson'])
        json[0]['uid'] == 'testUID'
        json[0]['cn'].containsAll(['testUID', 'This is a common name for the sub entry.'])
        json[0]['sn'] == 'This is the surname for the sub entry.'
        json[0]['displayName'] == 'This is name commonly displayed for the sub entry.'
        json[0]['employeeNumber'] == '11111'
        json[0]['description'] == 'This is an entry subordinate to o=deleteMe.'
        json[0]['userPassword'] == new String(Base64.encoder.encode('testpwd'.bytes))
    }
}
