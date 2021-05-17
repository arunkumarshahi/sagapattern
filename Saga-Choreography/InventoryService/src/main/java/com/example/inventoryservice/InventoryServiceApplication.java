package com.example.inventoryservice;

import com.example.inventoryservice.event.PaymentEvent;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@SpringBootApplication
@Slf4j
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

}


@Configuration
@Slf4j
//@EnableR2dbcRepositories(basePackages = "link.alab.reactiver2dbc")
class DBConnectiobConfig {

//    @KafkaListener(topics = "payment-event", groupId = "foo")
//    public void listenGroupFoo(PaymentEvent message) {
//        log.info("Received Message in group foo: {}" , message);
//    }
    //extends AbstractR2dbcConfiguration{
//	@Bean
////@Profile("test")
//	public ConnectionFactory connectionFactory() {
////		System.out.println(">>>>>>>>>> Using H2 in mem R2DBC connection factory");
//		 return new H2ConnectionFactory(
//	                H2ConnectionConfiguration.builder()
//	                        .url("mem:testdb;DB_CLOSE_DELAY=-1;TRACE_LEVEL_FILE=4")
//	                        .username("sa")
//	                        .build());
//	}
    @Bean
    public ConnectionFactoryInitializer databaseInitializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        // populator.addPopulators(new ResourceDatabasePopulator(new
        // ClassPathResource("schema/data.sql")));
        initializer.setDatabasePopulator(populator);

        return initializer;
    }
}

