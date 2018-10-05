package com.itemconfiguration.controller;


import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class IndexPageController {

    private ServletContext servletContext;

    public IndexPageController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @GetMapping("/**")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/resources/**")
    public ResponseEntity<byte[]> getResources(HttpServletRequest request) {
        InputStream in = servletContext.getResourceAsStream(request.getRequestURI());
        try {
            return new ResponseEntity<>(IOUtils.toByteArray(in), HttpStatus.OK);
        } catch (IOException e) {
            //I don't use logger, because this project is just help tool
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
