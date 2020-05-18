package com.biz.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EurekaClientController {
    
    @Value("${spring.application.name}")
    private String serviceId;

    @Value("${server.port}")
    private String servicePort;

    @GetMapping("/")
    public Object info() {
        return "Info from service:" + serviceId + " port:" + servicePort + "!";
    }

}