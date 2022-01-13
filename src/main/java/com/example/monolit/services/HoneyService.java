package com.example.monolit.services;

import java.util.List;
import java.util.Map;

public interface HoneyService {
    public List<String> getHoney(Integer flowerId);
    public void addHoney(String flower, Map<Integer, String> i);
    public void deleteHoney(Integer flowerId);
    public List<String> getAll(Integer limit);
}
