package com.deazoft.kafka.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Loan {
    @JsonProperty("loanId")
    private String loanId;

    public Loan(String loanId) {
        this.loanId = loanId;

    }
}
