FROM ghcr.io/graalvm/native-image-community:17-ol8 as graalvm

COPY . /home/app/micronaut-graal-app
WORKDIR /home/app/micronaut-graal-app

ARG QUICK_BUILD
RUN native-image --verbose ${QUICK_BUILD} -cp build/libs/*-all.jar



FROM gcr.io/distroless/base-debian11:latest

LABEL maintainer="devatherock@gmail.com"
LABEL io.github.devatherock.version="2.3.0"

EXPOSE 8080

COPY --from=graalvm /home/app/micronaut-graal-app/micronautgraalapp /micronaut-graal-app/micronautgraalapp
ENTRYPOINT ["/micronaut-graal-app/micronautgraalapp"]
