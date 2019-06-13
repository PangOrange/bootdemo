package com.orange.bootdemo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterceptorController {

    @RequestMapping(value = "/interceptor", method = RequestMethod.GET)
    public String interceptor  () {
        return "interceptor";
    }
}
