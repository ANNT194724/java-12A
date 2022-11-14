package com.example.bmi.service;

import com.example.bmi.exeception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class BmiService {
    public float getBmi(float height, float weight) {
        if (height <= 0 || weight <= 0) {
            throw new BadRequestException("Weight and height must be positive!");
        }
        return weight/(height*height);
    }
}
