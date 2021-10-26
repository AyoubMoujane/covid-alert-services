# COVID ALERT SERVICES

# Installation

###1. Build the jar

   Gradle > UserService > Tasks > build > bootjar

###2. Build an image of the service

   Gradle > UserService > Tasks > docker > docker

###3. Run the docker container 

```bash
docker compose up
```

# Stop the service 

To stop the service you need to run the command 

```bash
docker compose down -v
```