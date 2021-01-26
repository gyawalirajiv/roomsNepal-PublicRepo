package com.gyawalibros.controllers;

import com.gyawalibros.config.CloudinaryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class HomeController {

    @Autowired
    CloudinaryConfig cloudinaryConfig;


    @RequestMapping("/")
    public String home() throws IOException {

        return "index";

    }

}
