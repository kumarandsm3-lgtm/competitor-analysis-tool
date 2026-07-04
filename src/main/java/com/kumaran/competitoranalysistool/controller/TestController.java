package com.kumaran.competitoranalysistool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "AI Competitor Analysis Tool started successfully";
    }

    @GetMapping("/test")
    public String test() {
        return "Backend is working";
    }
}