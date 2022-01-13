package com.example.monolit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.springframework.context.annotation.Profile;

@Service
@Profile("FlowerServiceImpl")
public class FlowerServiceImpl implements FlowerService {

    @Autowired
    HoneyService honeyService;

    Map<Integer, String> flowers = new HashMap<>();

    int id = 0;

    private void createFlowers() {
        flowers.put(++id, "Tymianek");
        flowers.put(++id, "Nagietek");
        flowers.put(++id, "Tulipan");
        flowers.put(++id, "Bazylia");
        flowers.put(++id, "Rumianek");
    }

    public FlowerServiceImpl() {
        createFlowers();
    }

    @Override
    public String getFlower(Integer flowerId) {
        return flowers.get(flowerId);
    }

    @Override
    public void addFlower(String flower) {
        flowers.put(++id, flower);
    }

    @Override
    public void deleteFlower(Integer flowerId) {
        flowers.remove(flowerId);
    }
}
