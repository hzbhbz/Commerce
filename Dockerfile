FROM gradle:7.1.0-jdk11

USER root

RUN useradd -ms /bin/bash wasadm

RUN rm -rf /app
RUN mkdir -p /app

RUN chown -R wasadm:wasadm /app

RUN cd /app
RUN git clone https://github.com/hzbhbz/Commerce-Common.git /app/Commerce-Common
RUN git clone https://github.com/hzbhbz/Commerce.git /app/Commerce

WORKDIR /app/Commerce

RUN cd /app/Commerce

RUN chmod -R 755 /app/*

RUN gradle build

CMD ["java", "-jar", "/app/Commerce/build/libs/Commerce-0.0.1-SNAPSHOT.jar"]
