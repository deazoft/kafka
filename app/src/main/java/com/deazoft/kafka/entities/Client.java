package com.deazoft.kafka.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}