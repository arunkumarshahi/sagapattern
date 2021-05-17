call cls
call mvn clean install -DskipTests
call docker build -t arunkumarshahi/inventoryservice .
call docker-compose up -d
call docker ps