package io.github.devatherock.ldapsearch.service

import javax.naming.NamingEnumeration
import javax.naming.directory.Attribute
import javax.naming.directory.Attributes
import javax.naming.ldap.LdapContext

import io.github.devatherock.ldapsearch.config.LdapProperties

import spock.lang.Specification
import spock.lang.Subject

/**
 * Test class for {@link LdapSearchService}
 */
class LdapSearchServiceSpec extends Specification {

    @Subject
    LdapSearchService service

    LdapProperties config = new LdapProperties(
            host: 'ldap://localhost:33389',
            username: 'testuser',
            password: 'testpwd',
    )

    void setup() {
        config.host = 'ldap://localhost:33389'
        config.binaryAttributes = ['binOne', 'binTwo']
        config.init()

        service = new LdapSearchService(config)
    }

    void 'test initialize ldap environment'() {
        when:
        Map ldapEnvironment = service.initializeLdapEnvironment()

        then:
        ldapEnvironment.size() == 8
        ldapEnvironment['java.naming.factory.initial'] == 'com.sun.jndi.ldap.LdapCtxFactory'
        ldapEnvironment['java.naming.provider.url'] == 'ldap://localhost:33389'
        ldapEnvironment['java.naming.security.authentication'] == 'simple'
        ldapEnvironment['java.naming.security.principal'] == 'testuser'
        ldapEnvironment['java.naming.security.credentials'] == 'testpwd'
        ldapEnvironment['com.sun.jndi.ldap.connect.pool'] == 'true'
        ldapEnvironment['com.sun.jndi.ldap.read.timeout'] == '10000'
        ldapEnvironment['java.naming.ldap.attributes.binary'] == 'binOne binTwo'
    }

    void 'test find base dn'() {
        given:
        LdapContext context = Mock()
        Attributes attributes = Mock()
        NamingEnumeration attributeNames = Mock()
        Attribute attribute = Mock()
        NamingEnumeration attributeValues = Mock()

        when:
        String baseDn = service.findBaseDn(null, context)

        then:
        1 * context.getAttributes('', ['namingContexts'] as String[]) >> attributes
        1 * attributes.getIDs() >> attributeNames
        attributeNames.hasMoreElements() >>> [true, false]
        1 * attributeNames.next() >> 'namingContexts'
        1 * attributes.get('namingContexts') >> attribute
        1 * attribute.getAll() >> attributeValues
        attributeValues.hasMoreElements() >>> [true, true, false]
        attributeValues.next() >>> ['dc=example,dc=com', 'ou=Users,dc=example,dc=com']
        1 * attributeValues.close()
        1 * attributeNames.close()

        and:
        baseDn == 'dc=example,dc=com'
    }
}
