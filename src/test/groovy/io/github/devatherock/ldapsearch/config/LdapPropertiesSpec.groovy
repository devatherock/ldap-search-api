package io.github.devatherock.ldapsearch.config

import org.slf4j.LoggerFactory

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
}
