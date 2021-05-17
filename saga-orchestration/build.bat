call cls
 docker compose down
 call mvn clean install -DskipTests

 cd inventory-service
rem  call docker build -t arunkumarshahi/inventoryservice .

 cd ..
  cd order-orchestrator
  call docker build -t arunkumarshahi/orchestrationservice .
 
  cd ..
  cd order-service
 rem call docker build -t arunkumarshahi/orderservice .
 
  cd ..
  cd payment-service
 rem call docker build -t arunkumarshahi/paymentservice .
 
cd ..
call docker-compose up -d
rem call docker ps
rem call docker logs --follow  saga-choreography_paymentservice_1

 call docker logs --follow  payment