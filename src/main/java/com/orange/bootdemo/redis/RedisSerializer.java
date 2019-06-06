package com.orange.bootdemo.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RedisSerializer implements Serializable {
    private Integer id;
    private String name;
    private Double aDouble;
    private List<String> list = new ArrayList();

    public RedisSerializer() {
        this.id = 1;
        this.name = "程";
        this.aDouble = 25.0;
        List<String> alist = new ArrayList<String>();
        alist.add("123");
        alist.add("456");
        this.list = alist;
    }
}
