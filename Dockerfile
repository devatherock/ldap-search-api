FROM adoptopenjdk/openjdk11-openj9:jre-11.0.8_10_openj9-0.21.0-alpine
COPY build/libs/ldap-search-api-*-all.jar ldap-search-api.jar
EXPOSE 8080
CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar ldap-search-api.jar
