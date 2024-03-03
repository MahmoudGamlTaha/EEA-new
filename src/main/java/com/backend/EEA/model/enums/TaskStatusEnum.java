package com.backend.EEA.model.enums;

public enum TaskStatusEnum {
    Queued(0), Started(1), In_Progress(2), Done(3);

    private final int type;

    TaskStatusEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
