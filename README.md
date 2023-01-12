# Posts
### Table of Contents
- [Installation](#installation)
  - [Frontend](#frontend)
  - [Docker](#docker)
    - [CLI](#cli)
      - [Start](#start)
      - [Stop](#stop)
      - [Rebuild](#rebuild)
    - [Makefile](#)
- [Screenshots]()

# Installation

## Frontend
```shell
cd client
npm install
npm run dev
```
Go to `localhost:3000`

## Docker
> Required docker

Go to dev folder
```shell
cd images/dev
```

### CLI
#### Start
To start docker container:
```shell
docker-compose up
```
Or if you prefer running in background:
```shell
docker-compose up -d
```

#### Stop
To stop container:
```shell
docker-compose stop
```

#### Rebuild 
To remove docker container with content: 
```shell
docker-compose rm -f
docker-compose up
```

### Makefile
> Required GNU Make
```shell
# Start container
make

# Rebuild
make clean

# Remove container and images
make delete

# Connect into database cli
make tap
```

### Screenshots

![](./docs/screenshot/1.png)

![](./docs/screenshot/2.png)

### Editor
![](./docs/screenshot/3.png)

![](./docs/screenshot/4.png)

![](./docs/screenshot/5.png)

P.S Roma
