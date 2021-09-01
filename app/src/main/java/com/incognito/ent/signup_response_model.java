package com.incognito.ent;

public class signup_response_model {
    String message;
    // generate constructor
    public signup_response_model(String message) {
        this.message = message;
    }
    // generate blank constructor
    public signup_response_model() {
    }
    // generate getter setter


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
