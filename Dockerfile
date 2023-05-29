FROM eclipse-temurin:17.0.7_7-jre-ubi9-minimal
COPY build/libs/ldap-search-api-*-all.jar ldap-search-api.jar
EXPOSE 8080
CMD java -Dcom.sun.management.jmxremote ${JAVA_OPTS} -jar ldap-search-api.jar
