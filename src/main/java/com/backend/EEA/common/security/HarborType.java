package com.backend.EEA.common.security;

public enum HarborType {
    Sea("SEA"),
    River("RIVER")
    ;

    HarborType(String type) {
    }
    String name(HarborType type){
        return type.toString().toLowerCase();
    }
}
