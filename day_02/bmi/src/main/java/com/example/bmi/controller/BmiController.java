package com.example.bmi.controller;

import com.example.bmi.request.BmiRequest;
import com.example.bmi.service.BmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BmiController {
    @Autowired
    BmiService bmiService;

    @GetMapping("/bmi")
    public float getBmi(@RequestParam float height, @RequestParam float weight) {
        return bmiService.getBmi(height,weight);
    }

    @PostMapping("/bmi")
    public float getBmi(@RequestBody BmiRequest bmiRequest) {
        return getBmi(bmiRequest.getHeight(), bmiRequest.getWeight());
    }
}
