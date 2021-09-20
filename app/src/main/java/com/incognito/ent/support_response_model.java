package com.incognito.ent;

public class support_response_model {
    String message;
    // generate constructor
    public support_response_model(String message) {
        this.message = message;
    }
    // generate blank constructor
    public support_response_model() {
    }
    // generate getter setter


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }}