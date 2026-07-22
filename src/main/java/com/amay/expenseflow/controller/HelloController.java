package com.amay.expenseflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Map<String, String> hello() {

        Map<String, String> response = new HashMap<>();

        response.put("message", "Welcome to ExpenseFlow");
        response.put("status", "Application Running");
        response.put("version", "1.0");

        return response;
    }
}