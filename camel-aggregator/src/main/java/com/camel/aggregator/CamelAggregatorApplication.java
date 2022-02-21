package com.camel.aggregator;

import org.apache.camel.component.leveldb.LevelDBAggregationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CamelAggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelAggregatorApplication.class, args);
    }

    @Bean
    LevelDBAggregationRepository levelDBAggregationRepo() {
        return new LevelDBAggregationRepository("myrepo", "data/myrepo.dat");
    }

}
