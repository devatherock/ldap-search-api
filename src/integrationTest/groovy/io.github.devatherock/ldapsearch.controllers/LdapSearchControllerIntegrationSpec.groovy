package io.github.devatherock.ldapsearch.controllers

import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig
import io.micronaut.test.extensions.spock.annotation.MicronautTest

/**
 * Integration test for {@link LdapSearchController} with auth
 */
@MicronautTest(propertySources = 'classpath:application-integration.yml', startApplication = false)
class LdapSearchControllerIntegrationSpec extends LdapSearchControllerSpec {

    protected void customizeLdapConfig(InMemoryDirectoryServerConfig config) {
        config.addAdditionalBindCredentials('cn=Directory Manager', 'testpwd')
    }

    protected String getExpectedJson() {
        '''
        [
          {
            "userPassword" : "YWJjZGU=",
            "uid" : "sclaus",
            "objectClass" : [
              "top",
              "person",
              "organizationalPerson",
              "inetOrgPerson"
            ],
            "sn" : "Claus",
            "cn" : "Santa Claus"
          }
        ]
        '''.stripIndent().trim()
    }
}
