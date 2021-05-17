call cls
docker compose down
 cd InventoryService
 call mvn clean install -DskipTests
 call docker build -t arunkumarshahi/inventoryservice .

 rem cd ..
rem cd PaymentService
rem call mvn clean install -DskipTests
rem call docker build -t arunkumarshahi/paymentservice .

cd ..
call docker-compose up -d
rem call docker ps
rem call docker logs --follow  saga-choreography_paymentservice_1

 call docker logs --follow saga-choreography_inventoryservice_1