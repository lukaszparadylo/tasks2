package com.crud2.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {
    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("variable", "My Thymeleaf variable");
        model.put("two", 2);
        model.put("twoStr", "2");
        model.put("add", "+");
        model.put("multiply", "*");
        model.put("subtract", "-");
        model.put("equal", "=");
        return "index";
    }
}
