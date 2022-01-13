package com.tool.communication_model;
// ZMIENIONE settery i no arg constructor

public class RemoteArgument {
    private String value;
    private RemoteType type;

    public RemoteArgument(){}
    public RemoteArgument(
            String value,
            RemoteType type
    ){
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public RemoteType getType() {
        return type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(RemoteType type) {
        this.type = type;
    }
}
