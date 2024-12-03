package io.github.devatherock.ldapsearch.config

import org.slf4j.LoggerFactory

import io.github.devatherock.ldapsearch.config.LdapProperties.AuthenticationType

import ch.qos.logback.classic.Level
import spock.lang.Specification
import spock.lang.Subject

/**
 * Test class for {@link LdapProperties}
 */
class LdapPropertiesSpec extends Specification {

    @Subject
    LdapProperties config = new LdapProperties(
            binaryAttributes: ['binOne', 'binTwo'],
            connectionPool: new LdapProperties.LdapConnectionPoolProperties(enabled: false)
    )

    def logger = LoggerFactory.getLogger('io.github.devatherock.ldapsearch.config.LdapProperties')
    String connectionPoolDebugProperty = 'com.sun.jndi.ldap.connect.pool.debug'

    void 'test init'() {
        given:
        logger.setLevel(Level.DEBUG)

        when:
        config.init()

        then:
        System.properties[connectionPoolDebugProperty] == 'fine'
        System.properties['com.sun.jndi.ldap.connect.pool.protocol'] == 'plain ssl'
        config.combinedBinaryAttributes == 'binOne binTwo'

        cleanup:
        logger.setLevel(Level.INFO)
        System.clearProperty(connectionPoolDebugProperty)
    }

    void 'test is valid ldap credentials'() {
        given:
        config.authType = authType
        config.username = username
        config.password = password

        when:
        boolean actual = config.isValidLdapCredentials()

        then:
        actual == expected

        where:
        username | password | authType                  || expected
        'dummy'  | 'dummy'  | AuthenticationType.SIMPLE || true
        ''       | ''       | AuthenticationType.SIMPLE || false
        'dummy'  | null     | AuthenticationType.SIMPLE || false
        null     | 'dummy'  | AuthenticationType.SIMPLE || false
        ''       | ''       | AuthenticationType.NONE   || true
        'dummy'  | 'dummy'  | AuthenticationType.NONE   || true
        null     | null     | AuthenticationType.NONE   || true
    }
}
