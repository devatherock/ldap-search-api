package io.github.devatherock.ldapsearch.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import io.github.devatherock.ldapsearch.config.LdapProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service to search LDAP
 * 
 * @author devaprasadh
 *
 */
@Slf4j
@Singleton
@RequiredArgsConstructor
public class LdapSearchService {
    /**
     * The attribute id to use when querying LDAP for the base DN
     */
    private static final String ATTR_ID_BASE_DN = "namingContexts";

    private final LdapProperties config;

    /**
     * Searches LDAP under the given base DN with the supplied filter criteria
     * 
     * @param inputBaseDn
     * @param filter
     * @return the search results
     * @throws NamingException
     */
    public List<Map<String, Object>> search(String inputBaseDn, String filter) throws NamingException {
        // If the credentials are not valid, the constructor
        // will throw an exception
        LdapContext ldapContext = new InitialLdapContext(initializeLdapEnvironment(), null);

        // Find base DN
        String baseDn = findBaseDn(inputBaseDn, ldapContext);

        // Search options
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        ldapContext.setRequestControls(null);

        NamingEnumeration<SearchResult> results = null;
        Map<String, Object> transformedResult = null;
        List<Map<String, Object>> finalResult = new ArrayList<>();

        try {
            // Execute the search
            results = ldapContext.search(baseDn, filter, searchControls);

            while (results.hasMoreElements()) {
                transformedResult = new HashMap<>();
                readAttributes(transformedResult, results.next().getAttributes());
                finalResult.add(transformedResult);
            }
            results.close();
        } catch (NameNotFoundException e) {
            LOGGER.info("No results for '{}'", filter, e);
        } finally {
            ldapContext.close();
        }

        return finalResult;
    }

    /**
     * Initializes the LDAP environment with host, username, password and other
     * config
     * 
     * @return the ldap environment
     */
    private Hashtable<String, String> initializeLdapEnvironment() {
        Hashtable<String, String> ldapEnv = new Hashtable<String, String>();
        ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        ldapEnv.put(Context.PROVIDER_URL, config.getHost());
        ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
        ldapEnv.put(Context.SECURITY_PRINCIPAL, config.getUsername());
        ldapEnv.put(Context.SECURITY_CREDENTIALS, config.getPassword());
        ldapEnv.put("com.sun.jndi.ldap.connect.pool", String.valueOf(config.getConnectionPool().isEnabled()));
        ldapEnv.put("com.sun.jndi.ldap.read.timeout", String.valueOf(config.getReadTimeoutMillis()));

        if (config.getCombinedBinaryAttributes().length() > 0) {
            ldapEnv.put("java.naming.ldap.attributes.binary", config.getCombinedBinaryAttributes());
        }

        return ldapEnv;
    }

    /**
     * Finds the base DN by querying LDAP if the supplied one is null
     * 
     * @param inputBaseDn
     * @param ldapContext
     * @return the base DN
     * @throws NamingException
     */
    private String findBaseDn(String inputBaseDn, LdapContext ldapContext) throws NamingException {
        String baseDn = null != inputBaseDn ? inputBaseDn : config.getBaseDn();

        if (null == baseDn) {
            Attributes baseDnAttributes = ldapContext.getAttributes("", new String[] { ATTR_ID_BASE_DN });
            Map<String, Object> baseDnMap = new LinkedHashMap<>();
            readAttributes(baseDnMap, baseDnAttributes);
            LOGGER.debug("Base DNs: {}", baseDnMap);

            Object baseDns = baseDnMap.get(ATTR_ID_BASE_DN);
            if (baseDns instanceof List) {
                baseDn = ((List<String>) baseDns).get(0);
            } else {
                baseDn = (String) baseDns;
            }
        }

        return baseDn;
    }

    /**
     * Reads LDAP attributes into the supplied map, formatting and merging values as
     * required
     * 
     * @param result
     * @param attributes
     * @throws NamingException
     */
    private void readAttributes(Map<String, Object> result, Attributes attributes) throws NamingException {
        NamingEnumeration<String> attributeNames = attributes.getIDs();
        String attributeName = null;
        NamingEnumeration<?> attributeValues = null;
        Object existingAttributeValue = null;
        Object attributeValue = null;

        while (attributeNames.hasMoreElements()) {
            attributeName = attributeNames.next();
            attributeValues = attributes.get(attributeName).getAll();

            while (attributeValues.hasMoreElements()) {
                attributeValue = formatAttributeValue(attributeValues.next());
                existingAttributeValue = result.get(attributeName);

                if (existingAttributeValue instanceof List) {
                    ((List<Object>) existingAttributeValue).add(attributeValue);
                } else if (null != existingAttributeValue) {
                    List<Object> newAttributeValue = new ArrayList<>();
                    newAttributeValue.add(existingAttributeValue);
                    newAttributeValue.add(attributeValue);

                    result.put(attributeName, newAttributeValue);
                } else {
                    result.put(attributeName, attributeValue);
                }
            }
            attributeValues.close();
        }
        attributeNames.close();
    }

    /**
     * Formats the attribute value if required
     * 
     * @param attributeValue
     * @return the formatted attribute value
     */
    private Object formatAttributeValue(Object attributeValue) {
        if (attributeValue instanceof byte[]) {
            return new String(Base64.getEncoder().encode((byte[]) attributeValue));
        } else {
            return attributeValue;
        }
    }
}