package com.camel.aggregator.controller;

import lombok.AllArgsConstructor;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.camel.aggregator.Constants.ABC_ENDPOINT;
import static com.camel.aggregator.Constants.MULTIPLE_AGGREGATE;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    ProducerTemplate producerTemplate;

    @GetMapping("/aggregate-ABC")
    public String badBean() {
        producerTemplate.sendBodyAndHeader(ABC_ENDPOINT, "A", "myId", 1);
        producerTemplate.sendBodyAndHeader(ABC_ENDPOINT, "B", "myId", 1);
        producerTemplate.sendBodyAndHeader(ABC_ENDPOINT, "F", "myId", 2);
        producerTemplate.sendBodyAndHeader(ABC_ENDPOINT, "C", "myId", 1);
        producerTemplate.sendBodyAndHeader(ABC_ENDPOINT, "F", "myId", 2);
        producerTemplate.sendBodyAndHeader(ABC_ENDPOINT, "F", "myId", 2);
        return "done";
    }

    @GetMapping("/MultipleConditionAggregate")
    public String goodBean() {
        producerTemplate.sendBody(MULTIPLE_AGGREGATE,
                "<order name=\"motor\" amount=\"1000\" customer=\"honda\"/>");
        producerTemplate.sendBody(MULTIPLE_AGGREGATE,
                "<order name=\"motor\" amount=\"500\" customer=\"toyota\"/>");
        producerTemplate.sendBody(MULTIPLE_AGGREGATE,
                "<order name=\"gearbox\" amount=\"200\" customer=\"toyota\"/>");
        return "done";
    }
}
