package io.github.devatherock.ldapsearch.controllers

import io.micronaut.test.extensions.spock.annotation.MicronautTest

/**
 * Integration test for {@link LdapSearchController}
 */
@MicronautTest(propertySources = 'classpath:application-integration.yml', startApplication = false)
class LdapSearchControllerIntegrationSpec extends LdapSearchControllerSpec {

    protected String getExpectedJson() {
        '''
        [
          { 
            "uid" : "sclaus",
            "userPassword" : "YWJjZGU=",
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
