package dev.binhcn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

//    @RequestMapping("{?:(?:(?!api|static|\\.).)*}/**")
    @RequestMapping("/binhcn")
    public String redirectApi() {
        return "/index.html";
    }
}