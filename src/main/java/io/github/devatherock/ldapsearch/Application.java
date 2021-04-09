package io.github.devatherock.ldapsearch;

import io.github.devatherock.ldapsearch.Application;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "ldap-search-api", version = "0.1.0"))
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
