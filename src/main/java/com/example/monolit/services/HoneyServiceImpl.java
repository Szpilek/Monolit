package com.example.monolit.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HoneyServiceImpl implements HoneyService{
    Map<Integer, String> flowers = new HashMap<>();
    int id = 0;

    private void createFlowers(){
        flowers.put(++id ,"Tymianek");
        flowers.put(++id ,"Nagietek");
        flowers.put(++id ,"Tulipan");
        flowers.put(++id ,"Bazylia");
        flowers.put(++id ,"Rumianek");
    }

    public HoneyServiceImpl(){
        createFlowers();
    }

    @Override
    public String getHoney(Integer flowerId) {
        return flowers.get(flowerId);
    }

    @Override
    public void addHoney(String flower) {
        flowers.put(++id ,flower);
    }

    @Override
    public void deleteHoney(Integer flowerId) {
        flowers.remove(flowerId);
    }
}
