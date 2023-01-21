package com.example.monolit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AServiceImpl implements AService {

    @Autowired
    BService bService;

    
    @Override
    public String call() {
      return "doneA";
    }

    @Override
    public String callB() {
       return bService.call();
    }

}
