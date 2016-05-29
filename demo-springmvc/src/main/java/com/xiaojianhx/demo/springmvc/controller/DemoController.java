package com.xiaojianhx.demo.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @ResponseBody
    @RequestMapping("/demo")
    public Map login() {

        Map map = new HashMap();
        map.put("code", "0");
        map.put("data", "success");

        return map;
    }
}