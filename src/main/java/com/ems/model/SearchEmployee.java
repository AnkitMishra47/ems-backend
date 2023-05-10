package com.ems.model;

import org.springframework.stereotype.Component;

@Component
public class SearchEmployee {
    public SearchEmployee() {
    }

    private String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public SearchEmployee(String details) {
        this.details = details;
    }
}
