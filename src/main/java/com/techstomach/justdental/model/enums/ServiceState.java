package com.techstomach.justdental.model.enums;

public enum ServiceState {
    STARTED("STARTED"),
    STOPPED("STOPPED");

    private final String state;

    ServiceState(String state) {
        this.state = state;
    }
}
