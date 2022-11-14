package com.example.randomcolor.controller;

import com.example.randomcolor.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColorController {
    @Autowired
    ColorService colorService;

    @GetMapping("/random-color")
    public String getRandomColor(@RequestParam int type) {
        return colorService.randomColor(type);
    }
}
