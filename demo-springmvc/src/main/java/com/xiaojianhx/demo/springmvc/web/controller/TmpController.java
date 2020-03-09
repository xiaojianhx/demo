package com.xiaojianhx.demo.springmvc.web.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
public class TmpController {

    @RequestMapping(value = "/upload")
    public String upload(HttpServletRequest request, ModelMap model) {

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {

            // 将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            // 获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                // 一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String path = "/tmp/" + file.getOriginalFilename();
                    try {
                        file.transferTo(new File(path));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    model.addAttribute("fileUrl", "/tmp/" + file.getOriginalFilename());
                }
            }
        }

        return "upload_result";
    }
}
