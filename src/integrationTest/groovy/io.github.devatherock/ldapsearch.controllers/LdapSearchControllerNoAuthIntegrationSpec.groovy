package io.github.devatherock.ldapsearch.controllers

import io.micronaut.test.extensions.spock.annotation.MicronautTest

/**
 * Integration test for {@link LdapSearchController} without auth
 */
@MicronautTest(propertySources = 'classpath:application-integration.yml', startApplication = false)
class LdapSearchControllerNoAuthIntegrationSpec extends LdapSearchControllerSpec {

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
