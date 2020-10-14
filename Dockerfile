FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ARG SPRING_ENV
ENV ACTIVE_SPRING_PROFILES=$SPRING_ENV
EXPOSE 10000
COPY target/${JAR_FILE} app.jar
RUN apk --no-cache add ca-certificates
RUN wget -q -O /etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub
RUN wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.29-r0/glibc-2.29-r0.apk
RUN wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.29-r0/glibc-bin-2.29-r0.apk
RUN apk add glibc-2.29-r0.apk glibc-bin-2.29-r0.apk
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -Dspring.profiles.active=$SPRING_ENV -jar /app.jar" ]
