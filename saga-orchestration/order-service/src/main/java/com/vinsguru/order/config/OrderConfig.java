package com.vinsguru.order.config;

import com.vinsguru.dto.OrchestratorRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.FluxSink;

@Configuration
@Slf4j
public class OrderConfig {

    @Bean
    public DirectProcessor<OrchestratorRequestDTO> publisher(){
        return DirectProcessor.create();
    }

    @Bean
    public FluxSink<OrchestratorRequestDTO> sink(DirectProcessor<OrchestratorRequestDTO> publisher){
        log.info("Publising event from method OrderConfig.sink");
        return publisher.sink();
    }

}
