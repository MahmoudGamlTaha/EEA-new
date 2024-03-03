package com.backend.EEA.common.security;

public enum Roles {
    CUSTOMER("customer"),
    USER("user");

    Roles(String customer) {

    }
    String name(Roles role){
        return role.toString().toLowerCase();
    }
}
