package io.github.devatherock.ldapsearch.config;

import javax.net.ssl.SSLSocketFactory;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.pattern.DateConverter;
import ch.qos.logback.classic.pattern.LevelConverter;
import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import ch.qos.logback.classic.pattern.LoggerConverter;
import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.pattern.ThreadConverter;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.OutputStreamAppender;
import ch.qos.logback.core.pattern.PatternLayoutEncoderBase;
import com.sun.jndi.ldap.LdapCtxFactory;
import io.micronaut.core.annotation.ReflectionConfig;
import io.micronaut.core.annotation.TypeHint.AccessType;
import net.logstash.logback.encoder.LogstashEncoder;

@ReflectionConfig(type = LogstashEncoder.class, accessType = AccessType.ALL_DECLARED_CONSTRUCTORS)
@ReflectionConfig(type = LdapCtxFactory.class, accessType = AccessType.ALL_DECLARED_CONSTRUCTORS)
@ReflectionConfig(type = ConsoleAppender.class, accessType = {
        AccessType.ALL_DECLARED_CONSTRUCTORS,
        AccessType.ALL_DECLARED_METHODS,
        AccessType.ALL_DECLARED_FIELDS,
        AccessType.ALL_PUBLIC,
})
@ReflectionConfig(type = OutputStreamAppender.class, accessType = {
        AccessType.ALL_DECLARED_CONSTRUCTORS,
        AccessType.ALL_DECLARED_METHODS,
        AccessType.ALL_DECLARED_FIELDS,
        AccessType.ALL_PUBLIC,
})
@ReflectionConfig(type = PatternLayoutEncoder.class, accessType = AccessType.ALL_DECLARED_CONSTRUCTORS)
@ReflectionConfig(type = PatternLayoutEncoderBase.class, accessType = {
        AccessType.ALL_DECLARED_CONSTRUCTORS,
        AccessType.ALL_DECLARED_METHODS,
        AccessType.ALL_DECLARED_FIELDS,
        AccessType.ALL_PUBLIC,
})
@ReflectionConfig(type = DateConverter.class, accessType = AccessType.ALL_DECLARED_CONSTRUCTORS)
@ReflectionConfig(type = ThreadConverter.class, accessType = AccessType.ALL_DECLARED_CONSTRUCTORS)
@ReflectionConfig(type = LevelConverter.class, accessType = AccessType.ALL_DECLARED_CONSTRUCTORS)
@ReflectionConfig(type = LoggerConverter.class, accessType = AccessType.ALL_DECLARED_CONSTRUCTORS)
@ReflectionConfig(type = MessageConverter.class, accessType = AccessType.ALL_DECLARED_CONSTRUCTORS)
@ReflectionConfig(type = LineSeparatorConverter.class, accessType = AccessType.ALL_DECLARED_CONSTRUCTORS)
@ReflectionConfig(type = SSLSocketFactory.class, accessType = AccessType.ALL_DECLARED_METHODS)
public class GraalConfig {
}
