# bouker-booking
![Build Status](https://travis-ci.org/bouker/bouker-booking.svg?branch=master)

## How to run?
In 'complete' folder:
    ./mvnw install dockerfile:build
    docker run -p 8080:8080 -t springio/gs-spring-boot-docker

In Internet browser: http://a.b.c.d:8080/ where a.b.c.d is IP address from command 'docker-machine ip'
