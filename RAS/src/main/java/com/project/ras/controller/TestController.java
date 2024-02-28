package com.project.ras.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class TestController {

    private final ScrapingService scrapingService;

    public TestController(ScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    @GetMapping("/test1")
    public String test1(){
        System.out.println("패킷");
        System.out.println(scrapingService);
        return "Main/MainPageTest1";
    }
}
