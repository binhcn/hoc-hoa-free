package dev.binhcn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/")
public class HomeController {

    @RequestMapping("{?:(?:(?!api|static|\\.).)*}/**")
    public String redirectApi() {
        return "/index.html";
    }
}