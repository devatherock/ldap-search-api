package io.github.devatherock.ldapsearch.config

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import io.micronaut.context.event.BeanCreatedEvent
import io.micronaut.jackson.JacksonConfiguration
import spock.lang.Specification
import spock.lang.Subject

/**
 * Test class for {@link ObjectMapperBeanEventListener}
 */
class ObjectMapperBeanEventListenerSpec extends Specification {
    @Subject
    ObjectMapperBeanEventListener listener

    JacksonConfiguration jacksonConfig = new JacksonConfiguration()
    ObjectMapper objectMapper = new ObjectMapper()

    void 'test on created - indent output enabled'() {
        given:
        BeanCreatedEvent event = Mock()
        jacksonConfig.serialization = [
                (SerializationFeature.INDENT_OUTPUT): true
        ]
        listener = new ObjectMapperBeanEventListener(jacksonConfig)

        when:
        listener.onCreated(event)

        then:
        1 * event.getBean() >> objectMapper

        and:
        def prettyPrinter = objectMapper._serializationConfig._defaultPrettyPrinter
        prettyPrinter instanceof DefaultPrettyPrinter
        prettyPrinter._arrayIndenter == DefaultIndenter.SYSTEM_LINEFEED_INSTANCE
    }

    void 'test on created - serialization config is empty'() {
        given:
        BeanCreatedEvent event = Mock()
        listener = new ObjectMapperBeanEventListener(jacksonConfig)

        when:
        listener.onCreated(event)

        then:
        1 * event.getBean() >> objectMapper

        and:
        def prettyPrinter = objectMapper._serializationConfig._defaultPrettyPrinter
        prettyPrinter._arrayIndenter != DefaultIndenter.SYSTEM_LINEFEED_INSTANCE
    }

    void 'test on created - indent output disabled'() {
        given:
        BeanCreatedEvent event = Mock()
        jacksonConfig.serialization = [
                (SerializationFeature.INDENT_OUTPUT): false
        ]
        listener = new ObjectMapperBeanEventListener(jacksonConfig)

        when:
        listener.onCreated(event)

        then:
        1 * event.getBean() >> objectMapper

        and:
        def prettyPrinter = objectMapper._serializationConfig._defaultPrettyPrinter
        prettyPrinter._arrayIndenter != DefaultIndenter.SYSTEM_LINEFEED_INSTANCE
    }
}
