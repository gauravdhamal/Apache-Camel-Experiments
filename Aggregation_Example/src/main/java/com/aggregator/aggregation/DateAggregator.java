package com.aggregator.aggregation;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DateAggregator implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if(Objects.isNull(oldExchange)){
            return newExchange;
        }
        String oldBody = oldExchange.getIn().getBody(String.class);
        String newBody = newExchange.getIn().getBody(String.class);
        List<String> list = new ArrayList<>();
        String aggregatedBody = oldBody + " -> " + newBody;
        list.add(aggregatedBody);
        oldExchange.getIn().setBody(list);
        return oldExchange;
    }
}
