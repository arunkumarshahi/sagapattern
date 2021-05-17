call cls
 docker compose down
 call mvn clean install -DskipTests

 cd inventory-service
  call docker build -t arunkumarshahi/inventoryservice .

 cd ..
  cd order-orchestrator
  call docker build -t arunkumarshahi/orchestrationservice .
 
  cd ..
  cd order-service
  call docker build -t arunkumarshahi/orderservice .
 
  cd ..
  cd payment-service
  call docker build -t arunkumarshahi/paymentservice .

 rem connect orchestrator serice to network1 bridge
 call docker network connect custom_frontend1 orchestration
cd ..
call docker-compose up -d
rem call docker ps
 call docker logs --follow  paymentservice

 rem call docker logs --follow  payment