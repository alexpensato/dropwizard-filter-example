package com.pensatocode.example.api;

public enum Country {
    USA("USA"),
    CANADA("CAN"),
    UNKNOWN("UNKNOWN");

    private final String value;

    Country(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
