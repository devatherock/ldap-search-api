package io.github.devatherock.ldapsearch.config;

import javax.inject.Singleton;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.jackson.JacksonConfiguration;
import lombok.RequiredArgsConstructor;

/**
 * Customizes the {@link ObjectMapper} bean as soon as it is created
 * 
 * @author devaprasadh
 *
 */
@Singleton
@RequiredArgsConstructor
public class ObjectMapperBeanEventListener implements BeanCreatedEventListener<ObjectMapper> {
    private final JacksonConfiguration jacksonConfig;

    @Override
    public ObjectMapper onCreated(BeanCreatedEvent<ObjectMapper> event) {
        final ObjectMapper objectMapper = event.getBean();

        // If pretty-print is enabled, pretty-print arrays as well
        if (CollectionUtils.isNotEmpty(jacksonConfig.getSerializationSettings()) &&
                Boolean.TRUE.equals(jacksonConfig.getSerializationSettings().get(SerializationFeature.INDENT_OUTPUT))) {
            DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
            prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
            objectMapper.setDefaultPrettyPrinter(prettyPrinter);
        }

        return objectMapper;
    }
}