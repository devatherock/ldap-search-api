package io.github.devatherock.ldapsearch.config;

import java.util.ArrayList;
import java.util.List;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Context;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.util.StringUtils;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Configurable properties for the application
 * 
 * @author devaprasadh
 *
 */
@Slf4j
@Getter
@Setter
@Context
@ConfigurationProperties("ldap")
public class LdapProperties {
    /**
     * The full host name of the LDAP server. Example:
     * {@code ldaps://ldap.jumpcloud.com:636}
     */
    @NotBlank(message = "ldap.host not specified")
    private String host;

    /**
     * The LDAP bind username. Could be a simple username like {@code devatherock}
     * or a DN like {@code uid=devatherock,ou=Users,dc=jumpcloud,dc=com} depending
     * on how the LDAP server is configured
     */
    private String username;

    /**
     * The LDAP bind password
     */
    private String password;

    /**
     * Value for the {@code java.naming.security.authentication} property. Defaults
     * to {@code simple}
     */
    private AuthenticationType authType = AuthenticationType.SIMPLE;

    /**
     * The default base DN to search against
     */
    private String baseDn;

    /**
     * Attributes in the search result that have binary values
     */
    private List<String> binaryAttributes = new ArrayList<>();
    @Setter(AccessLevel.NONE)
    private String combinedBinaryAttributes;

    /**
     * Read timeout for the search, in milliseconds. Defaults to 10 seconds
     */
    private long readTimeoutMillis = 10000;

    private LdapConnectionPoolProperties connectionPool = new LdapConnectionPoolProperties();

    @AssertTrue(message = "ldap.username or ldap.password not specified")
    public boolean isValidLdapCredentials() {
        return AuthenticationType.NONE.equals(authType) ||
                (StringUtils.hasText(username) && StringUtils.hasText(password));
    }

    /**
     * Connection pool configuration
     * 
     * @author devaprasadh
     */
    @Getter
    @Setter
    @Introspected
    @ConfigurationProperties("connection-pool")
    public static class LdapConnectionPoolProperties {
        /**
         * Indicates if a connection pool should be used
         */
        private boolean enabled = true;

        /**
         * Initial size of the connection pool
         */
        private int coreSize = 8;

        /**
         * Maximum size of the connection pool
         */
        private int maxSize = 8;

        /**
         * The total time a connection in the pool will be kept open, in milliseconds.
         * Defaults to 30 minutes
         */
        private long timeToLiveMillis = 1_800_000;
    }

    @Getter
    public enum AuthenticationType {
        NONE("none"),
        SIMPLE("simple");

        private final String code;

        AuthenticationType(String code) {
            this.code = code;
        }
    }

    @PostConstruct
    public void init() {
        combinedBinaryAttributes = String.join(" ", binaryAttributes);

        System.setProperty("com.sun.jndi.ldap.connect.pool.protocol", "plain ssl");
        if (connectionPool.isEnabled()) {
            System.setProperty("com.sun.jndi.ldap.connect.pool.maxsize", String.valueOf(connectionPool.maxSize));
            System.setProperty("com.sun.jndi.ldap.connect.pool.prefsize", String.valueOf(connectionPool.coreSize));
            System.setProperty("com.sun.jndi.ldap.connect.pool.timeout",
                    String.valueOf(connectionPool.timeToLiveMillis));
        }

        if (LOGGER.isDebugEnabled()) {
            System.setProperty("com.sun.jndi.ldap.connect.pool.debug", "fine");
        }
    }
}
