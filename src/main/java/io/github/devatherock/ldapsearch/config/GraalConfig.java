package io.github.devatherock.ldapsearch.config;

import javax.net.ssl.SSLSocketFactory;

import com.sun.jndi.ldap.LdapCtxFactory;
import io.micronaut.core.annotation.ReflectionConfig;
import io.micronaut.core.annotation.TypeHint.AccessType;
import net.logstash.logback.encoder.LogstashEncoder;

@ReflectionConfig(type = LogstashEncoder.class, accessType = AccessType.ALL_DECLARED_CONSTRUCTORS)
@ReflectionConfig(type = LdapCtxFactory.class, accessType = AccessType.ALL_DECLARED_CONSTRUCTORS)
@ReflectionConfig(type = SSLSocketFactory.class, accessType = AccessType.ALL_DECLARED_METHODS)
public class GraalConfig {
}
