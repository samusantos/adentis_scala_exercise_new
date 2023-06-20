## Requirements

docker version 24.0.2 or higher
docker compose version 2.18.1 or higher

## Getting started

Run the following command to set up the database:
```
docker compose -f ./docker/src/docker-compose.yml -f ./docker/src/docker-compose.local.yml up -d
```

Your application is now ready to be tested.
