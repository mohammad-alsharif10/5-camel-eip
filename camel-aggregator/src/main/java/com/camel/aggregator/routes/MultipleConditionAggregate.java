package com.camel.aggregator.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static com.camel.aggregator.Constants.MULTIPLE_AGGREGATE;

@Component
public class MultipleConditionAggregate extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from(MULTIPLE_AGGREGATE)
                .log("Sending ${body}")
                .aggregate(xpath("/order/@customer"), new MyAggregationStrategy())
                .completionSize(2).completionTimeout(5000)
                .log("Sending out ${body}");
    }
}
