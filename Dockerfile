FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 9999
ENTRYPOINT ["java","-cp","app:app/lib/*","com.wflair.metadata.MetadataApplication"]
## fix settings accordingly https://hackernoon.com/how-to-dockerize-any-application-b60ad00e76da