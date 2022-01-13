package com.example.monolit.services;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tool.communication_model.RemoteType;
import com.tool.communication_model.RemoteArgument;
import com.tool.communication_model.RemoteCall;
import org.springframework.context.annotation.Profile;

@Profile("FlowerServiceImpl")
public class HoneyServiceClient implements HoneyService {
    @Override
    public List<String> getHoney
            (Integer flowerId
            ) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String body = mapper.writeValueAsString(new RemoteCall("asd", "getHoney", List.of(new RemoteArgument(mapper.writeValueAsString(flowerId), new RemoteType(Integer.class.getName(), List.of())))));
            String mockReturn = "{}";
            return mapper.readValue(mockReturn, new TypeReference<List<String>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addHoney
            (String flower, Map<Integer, String> i
            ) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String body = mapper.writeValueAsString(new RemoteCall("asd", "addHoney", List.of(new RemoteArgument(mapper.writeValueAsString(flower), new RemoteType(String.class.getName(), List.of())), new RemoteArgument(mapper.writeValueAsString(i), new RemoteType(Map.class.getName(), List.of(new RemoteType(Integer.class.getName(), List.of()), new RemoteType(String.class.getName(), List.of())))))));
            String mockReturn = "{}";

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<String> getAll(Integer limit) {
        return List.of();
    }
    @Override
    public void deleteHoney
            (Integer flowerId
            ) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String body = mapper.writeValueAsString(new RemoteCall("asd", "deleteHoney", List.of(new RemoteArgument(mapper.writeValueAsString(flowerId), new RemoteType(Integer.class.getName(), List.of())))));
            String mockReturn = "{}";

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}