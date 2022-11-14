package com.example.randomcolor.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ColorService {
    public String randomColor(int type) {
        return switch (type) {
            case 1 -> randomColorName();
            case 2 -> randomHexColor();
            case 3 -> randomRgbColor();
            default -> throw new RuntimeException("Type không hợp lệ");
        };
    }

    private String randomColorName() {
        List<String> colors = List.of("black", "gray", "purple", "indigo",
                                            "blue", "teal", "green", "yellow",
                                            "orange", "pink", "red", "white");
        Random rand = new Random();
        return colors.get(rand.nextInt(colors.size()));
    }

    private String randomHexColor() {
        Random rand = new Random();
        int rand_num = rand.nextInt(0xffffff + 1);
        return String.format("#%06x", rand_num);
    }

    private String randomRgbColor() {
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return  "rgb(" + r + ", " + g + ", " + b + ")";
    }
}
