package com.nordstrom.teamterror.application.controllers;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nordstrom.teamterror.application.models.Application;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @RequestMapping(path = "/application", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createApplication(@RequestBody Application application) {
        HashMap<String,AttributeValue> item_values = new HashMap<>();

        item_values.put("application-id", new AttributeValue(UUID.randomUUID().toString()));
        item_values.put("account-member-id", new AttributeValue(application.getAccountMemberId()));
        item_values.put("listing-id", new AttributeValue(application.getListingId()));
        item_values.put("start-date", new AttributeValue(application.getStartDate().format(DateTimeFormatter
                .ISO_INSTANT)));
        item_values.put("end-date", new AttributeValue(application.getEndDate().format(DateTimeFormatter
                .ISO_INSTANT)));
        item_values.put("status", new AttributeValue("PENDING"));

        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration
                ("http://localhost:8000", "us-west-2");
        AmazonDynamoDBClientBuilder amazonDynamodDbBuilder = AmazonDynamoDBClientBuilder.standard();
        amazonDynamodDbBuilder.setEndpointConfiguration(endpointConfiguration);
        final AmazonDynamoDB ddb = amazonDynamodDbBuilder.build();

        ddb.putItem("team-terror-applications", item_values);
    }
}
