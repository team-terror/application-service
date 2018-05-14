package com.nordstrom.teamterror.application.controllers;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import java.util.HashMap;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @RequestMapping(path = "/application", method = RequestMethod.POST)
    public void createApplication() {
        HashMap<String,AttributeValue> item_values = new HashMap<>();

        item_values.put("application-id", new AttributeValue(UUID.randomUUID().toString()));

        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration
                ("http://localhost:8000", "us-west-2");
        AmazonDynamoDBClientBuilder amazonDynamodDbBuilder = AmazonDynamoDBClientBuilder.standard();
        amazonDynamodDbBuilder.setEndpointConfiguration(endpointConfiguration);
        final AmazonDynamoDB ddb = amazonDynamodDbBuilder.build();

        ddb.putItem("team-terror-applications", item_values);
    }
}
