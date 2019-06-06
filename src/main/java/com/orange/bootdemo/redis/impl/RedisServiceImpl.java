package com.orange.bootdemo.redis.impl;


import com.google.gson.Gson;
import com.orange.bootdemo.redis.RedisSerializer;
import com.orange.bootdemo.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service("redisService")
public class RedisServiceImpl implements RedisService {
    private final StringRedisTemplate template;

    @Autowired
    public RedisServiceImpl(StringRedisTemplate template) {
        this.template = template;
    }

    @Override
    public boolean set(final String key, final String value) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        ops.set(key, value);
        return true;
    }

    public boolean set(final String key, final Object value){
        Gson gson = new Gson();
        ValueOperations<String, String> ops = this.template.opsForValue();
        ops.set(key, gson.toJson(value));
        return true;
    }

    @Override
    public String get(final String key) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        String value = ops.get(key);
        if (StringUtils.isEmpty(value)) {
            throw new RuntimeException("the redis is null");
        }
        return value;
    }

    @Override
    public boolean expire(final String key, long expire) {
        return this.template.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public boolean remove(final String key) {
        return this.template.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.del(key.getBytes());
                return true;
            }
        });
    }
}

