FROM alpine:latest
RUN apk add --no-cache openjdk17-jre
CMD java -version
