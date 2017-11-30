FROM hypriot/rpi-java:latest
MAINTAINER Kevin Viet <kevin.viet@gmail.com>

WORKDIR /app
ADD ./target/chacon-switch-1.0-SNAPSHOT.jar /app

CMD java -jar /app/chacon-switch-1.0-SNAPSHOT.jar

