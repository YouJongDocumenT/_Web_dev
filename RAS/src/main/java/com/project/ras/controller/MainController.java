package com.project.ras.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String main(){
        return "Main/MainPage";
    }

    @GetMapping("/test1")
    public String Test1(){
        return "Main/MainPageTest1";
    }


}
