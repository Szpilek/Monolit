package com.example.monolit.services;

import org.springframework.stereotype.Service;

@Service
public class BServiceImpl implements BService {

    
    @Override
    public String call() {
      return "doneB";
    }


}
