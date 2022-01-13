package com.example.monolit.services;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;

@Service
@Profile("HoneyServiceImpl")
public class HoneyServiceImpl implements HoneyService {

    Map<Integer, String> flowers = new HashMap<>();

    int id = 0;

    private void createFlowers() {
        flowers.put(++id, "Tymianek");
        flowers.put(++id, "Nagietek");
        flowers.put(++id, "Tulipan");
        flowers.put(++id, "Bazylia");
        flowers.put(++id, "Rumianek");
    }

    public HoneyServiceImpl() {
        createFlowers();
    }

    @Override
    public List<String> getHoney(Integer flowerId) {
        return null;
    }

    @Override
    public List<String> getAll(Integer limit) {
        return flowers.values().stream().sorted().limit(limit).collect(Collectors.toList());
    }

    @Override
    public void addHoney(String flower, Map<Integer, String> i) {
        flowers.put(++id, flower);
    }

    @Override
    public void deleteHoney(Integer flowerId) {
        flowers.remove(flowerId);
    }
}
