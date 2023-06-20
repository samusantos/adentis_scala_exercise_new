FROM postgres:latest

RUN mkdir conf

COPY --chmod=775 ./resources/setup.sh /docker-entrypoint-initdb.d/
COPY --chmod=775 ./resources/setup.sql conf/
