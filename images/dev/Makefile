
all:
	docker-compose up

tap:
	docker exec -it  dev-postgres-1  psql -U root posts-db

clean:
	docker-compose rm -f

delete:
	docker-compose down --rmi all

.PHONY: tap clean delete
