package com.backend.EEA.model.enums;

public enum BankTypeEnum {
    ALL(0), ENTITY_BANK(1), DONNER_BANK(2);

    private final int type;

    BankTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
