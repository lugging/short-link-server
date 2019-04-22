package com.mimik.link.service.impl;

import com.mimik.link.enums.NumberTypeEnum;
import com.mimik.link.service.INumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisBasedSingleIdNumberGenerator implements INumberGenerator {

    private static final String ID_GEN_KEY = "number.%s.gen";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public Long nextNumber(NumberTypeEnum type) {
        String key = String.format(ID_GEN_KEY, type.toString().toLowerCase());
        return stringRedisTemplate.opsForValue().increment(key);
    }
}