package io.github.devatherock.ldapsearch.controllers

import io.micronaut.test.extensions.spock.annotation.MicronautTest

/**
 * Unit test for {@link LdapSearchController}
 */
@MicronautTest
class LdapSearchControllerTestSpec extends LdapSearchControllerSpec {

    protected String getExpectedJson() {
        '[{"uid":"sclaus","userPassword":"YWJjZGU=","objectClass":["top","person","organizationalPerson","inetOrgPerson"],"sn":"Claus","cn":"Santa Claus"}]'
    }
}
