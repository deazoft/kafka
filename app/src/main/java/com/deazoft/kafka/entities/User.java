package com.deazoft.kafka.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;

    @JsonProperty("email")
    private String email;

    public User(String firstName, String lastName, String thumbnailUrl, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.thumbnailUrl = thumbnailUrl;
        this.email = email;
    }

}