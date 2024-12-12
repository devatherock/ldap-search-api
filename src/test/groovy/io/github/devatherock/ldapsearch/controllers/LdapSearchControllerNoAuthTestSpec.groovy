package io.github.devatherock.ldapsearch.controllers

import io.micronaut.test.extensions.spock.annotation.MicronautTest

/**
 * Unit test for {@link LdapSearchController} without auth
 */
@MicronautTest(propertySources = 'classpath:application-test_no_auth.yml')
class LdapSearchControllerNoAuthTestSpec extends LdapSearchControllerSpec {

    protected String getExpectedJson() {
        '[{"userPassword":"YWJjZGU=","uid":"sclaus","objectClass":["top","person","organizationalPerson","inetOrgPerson"],"sn":"Claus","cn":"Santa Claus"}]'
    }
}
