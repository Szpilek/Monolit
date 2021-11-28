package com.example.monolit.api;

import com.example.monolit.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlowerControler {
//    @Autowired
    FlowerService flowerService;
    public FlowerControler(FlowerService flowerService){
        this.flowerService = flowerService;
    }

    @GetMapping("/flower/{flowerId}")
    public String getFlower(@PathVariable Integer flowerId){
        return flowerService.getFlower(flowerId);
    }

    @DeleteMapping("/flower/{flowerId}")
    public void deleteFlower(@PathVariable Integer flowerId){
        flowerService.deleteFlower(flowerId);
    }

    @PostMapping("/flower/add")
    public void addFlower(@RequestBody String flower){
        flowerService.addFlower(flower);
    }
}
