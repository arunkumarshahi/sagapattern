package com.example.paymentservice;

import com.example.paymentservice.model.UserBalance;
import com.example.paymentservice.repository.UserBalanceRepository;
import io.r2dbc.spi.ConnectionFactory;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }


}

@RequiredArgsConstructor
@Component
@Data
class DataLoader implements CommandLineRunner {

    private final UserBalanceRepository userBalanceRepository;


    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub



        this.userBalanceRepository.save(UserBalance.builder().balance(5000).build())
                .subscribe(System.out::println);

    }

}

@Configuration
//@EnableR2dbcRepositories(basePackages = "link.alab.reactiver2dbc")
class DBConnectiobConfig {
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
