# Project Makefile

# Default target
.DEFAULT_GOAL := help

# Build and start all services
up:
	docker-compose up --build

# Stop and remove all containers
down:
	docker-compose down

# Run tests
test:
	docker-compose run --rm app mvn test

# Clean project
clean:
	docker-compose run --rm app mvn clean

# Rebuild the app image
build:
	docker-compose build app

# Logs
logs:
	docker-compose logs -f app

# Help doc
help:
	@echo "Makefile commands:"
	@echo "  make up         - Build and start all services"
	@echo "  make down       - Stop and remove all containers"
	@echo "  make test       - Run tests"
	@echo "  make clean      - Clean the Maven project"
	@echo "  make build      - Rebuild the app Docker image"
	@echo "  make logs       - Follow application logs"
