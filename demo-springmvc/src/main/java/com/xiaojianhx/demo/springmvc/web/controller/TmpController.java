package com.xiaojianhx.demo.springmvc.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TmpController {

    @ResponseBody
    @RequestMapping(value = "/a/get")
    public Map get(HttpServletRequest request) {
        return request.getParameterMap();
    }
}
