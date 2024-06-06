package com.flipkart.beans;

public class Status {
    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "PingRequest{" +
                "input='" + input + '\'' +
                '}';
    }
}
