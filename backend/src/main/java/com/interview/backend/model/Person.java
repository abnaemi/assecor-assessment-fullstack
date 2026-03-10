package com.interview.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Person(
        Long id,
        @JsonProperty("lastname") String lastName,
        @JsonProperty("name") String firstName,
        @JsonProperty("zipcode") String zipCode,
        String city,
        String color
) {}