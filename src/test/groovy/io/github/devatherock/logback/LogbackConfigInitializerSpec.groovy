package io.github.devatherock.logback

import java.nio.file.Paths

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Test class for {@link LogbackConfigInitializer}
 */
class LogbackConfigInitializerSpec extends Specification {

    void 'test initialize config - no args'() {
        when:
        LogbackConfigInitializer.initializeConfig()

        then:
        noExceptionThrown()
    }

    @Unroll
    void 'test read config - #configFile'() {
        when:
        InputStream stream = LogbackConfigInitializer.readConfig(configFile)

        then:
        stream

        cleanup:
        stream?.close()

        where:
        configFile << [
            'logback.xml',
            Paths.get(System.properties['user.dir'], 'src/main/resources/logback.xml').toString(),
            'https://raw.githubusercontent.com/devatherock/artifactory-badge/master/src/main/resources/logback-json.xml'
        ]
    }

    @Unroll
    void 'test read config - non-existent/invalid path - #configFile'() {
        expect:
        !LogbackConfigInitializer.readConfig(configFile)

        where:
        configFile << [
            'classpath:logback.xml',
            'dummy.xml',
            Paths.get(System.properties['user.dir'], 'src/main/resources/logbackx.xml').toString(),
            'https://raw.githubusercontent.com/devatherock/artifactory-badge/master/src/main/resour/logback-json.xml'
        ]
    }

    @Unroll
    void 'test initialize config - #configFile'() {
        when:
        LogbackConfigInitializer.initializeConfig(configFile)

        then:
        noExceptionThrown()

        where:
        configFile << [
            'logback.xml',
            Paths.get(System.properties['user.dir'], 'src/main/resources/logback.xml').toString(),
            'https://raw.githubusercontent.com/devatherock/artifactory-badge/master/src/main/resources/logback-json.xml',
            'classpath:logback.xml',
            'logback-invalid.xml'
        ]
    }
}
