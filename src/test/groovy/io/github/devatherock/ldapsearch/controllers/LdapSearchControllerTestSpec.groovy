package io.github.devatherock.ldapsearch.controllers

import io.micronaut.test.extensions.spock.annotation.MicronautTest

/**
 * Unit test for {@link LdapSearchController}
 */
@MicronautTest
class LdapSearchControllerTestSpec extends LdapSearchControllerSpec {

    protected String getExpectedJson() {
        '[{"userPassword":"YWJjZGU=","uid":"sclaus","objectClass":["top","person","organizationalPerson","inetOrgPerson"],"sn":"Claus","cn":"Santa Claus"}]'
    }
}
