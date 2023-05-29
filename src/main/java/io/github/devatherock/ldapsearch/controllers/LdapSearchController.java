package io.github.devatherock.ldapsearch.controllers;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
import javax.naming.NamingException;

import io.github.devatherock.ldapsearch.service.LdapSearchService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import lombok.RequiredArgsConstructor;

/**
 * Controller to search LDAP
 * 
 * @author devaprasadh
 *
 */
@ExecuteOn(TaskExecutors.IO)
@Controller
@RequiredArgsConstructor
public class LdapSearchController {
    private final LdapSearchService searchService;

    /**
     * Searches LDAP under the given base DN with the supplied filter criteria
     * 
     * @param baseDn
     * @param filter
     * @param limit
     * @param requiredAttrs
     * @return the search results
     * @throws NamingException
     */
    @Get(value = "/search")
    public HttpResponse<List<Map<String, Object>>> search(@Nullable @QueryValue(value = "base_dn") String baseDn,
                                                          @QueryValue String filter,
                                                          @Nullable @QueryValue(defaultValue = "0") Integer limit,
                                                          @Nullable @QueryValue(value = "attributes") List<String> requiredAttrs)
            throws NamingException {
        HttpResponse<List<Map<String, Object>>> response = null;

        List<Map<String, Object>> responseBody = searchService.search(baseDn, filter, limit, requiredAttrs);
        if (responseBody.size() == 0) {
            response = HttpResponse.notFound(responseBody);
        } else {
            response = HttpResponse.ok(responseBody);
        }

        return response;
    }
}