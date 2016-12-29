FROM java:8
VOLUME /tmp
ADD /out/artifacts/catalog_service_2_jar/catalog-service-2.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]