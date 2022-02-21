package com.camel.aggregator.routes;

import lombok.AllArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.leveldb.LevelDBAggregationRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AggregateABCRouter extends RouteBuilder {

    private final MyAggregationStrategy myAggregationStrategy;
    private final LevelDBAggregationRepository levelDBAggregationRepository;

    @Override
    public void configure() throws Exception {
        from("direct:start")
                // do a little logging
                .log("Sending ${body} with correlation key ${header.myId}")
                // aggregate based on header correlation key
                // use class MyAggregationStrategy for aggregation
                // and complete when we have aggregated 3 messages
                .aggregate(header("myId"), myAggregationStrategy)
                .aggregationRepository(levelDBAggregationRepository)
                .completionSize(3)
                // do a little logging for the published message
                .log("Sending out ${body}");
    }
}
