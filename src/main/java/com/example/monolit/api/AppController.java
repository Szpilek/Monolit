package com.example.monolit.api;

import com.example.monolit.services.AService;
import com.example.monolit.services.BService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {
    @Autowired
    AService aService;
    @Autowired
    BService bService;

    ExecutorService executor = Executors.newFixedThreadPool(2);

    public AppController(AService aService, BService bService){
        this.aService = aService;
        this.bService = bService;
    }

    @GetMapping("/test0")
    public String testA(){
        
        return "Done";
    }

    @GetMapping("/test1")
    public String testB(){
      aService.call();
        return "done";
    }

    @GetMapping("/test2sync")
    public String testC(){
      aService.call();
      bService.call();
      return "done";
    }

    @GetMapping("/test2async")
    public String testD() throws InterruptedException{
      executor.invokeAll(List.of(
        aService::call,
        bService::call
      ));
      return "done";
    }

    @GetMapping("/test2chain")
    public String testE(){
      aService.callB();
      return "done";
    }
}
