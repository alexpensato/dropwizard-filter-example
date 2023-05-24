package com.pensatocode.example.api;

public enum Scope {
    IN("in"),
    OUT("out");

    private final String value;

    Scope(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
