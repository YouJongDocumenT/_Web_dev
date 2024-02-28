package com.project.ras.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

        System.out.println("패킷");

        return "Main/MainPageTest1";
    }

    @GetMapping("/new")
    public String New(){


        return "Main/New";
    }

}
