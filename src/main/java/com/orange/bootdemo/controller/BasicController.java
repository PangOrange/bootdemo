package com.orange.bootdemo.controller;

import com.orange.bootdemo.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @Autowired
    RedisService redisService;
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String baseDemo() {
        redisService.set("a","b");
        System.out.println(redisService.get("a"));
        return "Hello World";
    }
}
