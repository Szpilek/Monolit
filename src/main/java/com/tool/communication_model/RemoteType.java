package com.tool.communication_model;

import java.util.List;
// ZMIENIONE settery i no arg constructor

public class RemoteType {
 // te typy muszą być dostępne w kliencie i wrapperze więc muszą być wygenerowane w znanym miejscu źródeł starego projektu
    private String type;
    private List<RemoteType> parameters;
    public RemoteType(
            String type,
            List<RemoteType> parameters
    ){
        this.type = type;
        this.parameters = parameters;
    }

    public RemoteType() {
    }

    public String getType() {
        return type;
    }

    public List<RemoteType> getParameters() {
        return parameters;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParameters(List<RemoteType> parameters) {
        this.parameters = parameters;
    }
}
