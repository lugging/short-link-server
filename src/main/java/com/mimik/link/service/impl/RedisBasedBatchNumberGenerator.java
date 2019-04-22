package com.mimik.link.service.impl;

import com.google.common.collect.Lists;
import com.mimik.link.enums.NumberTypeEnum;
import com.mimik.link.service.INumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class RedisBasedBatchNumberGenerator implements INumberGenerator {

    private static final String ID_GEN_KEY = "number.%s.gen";

    private static final int BATCH_SIZE = 500;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 用于存储批量生成的 Number
    private List<Long> tmp = Lists.newArrayList();

    @Override
    public Long nextNumber(NumberTypeEnum type) {
        synchronized (tmp){
            // 如果 tmp 为空，将触发批量处理操作
            if (CollectionUtils.isEmpty(tmp)){
                String key = String.format(ID_GEN_KEY, type.toString().toLowerCase());
                long end = this.stringRedisTemplate.opsForValue().increment(key, BATCH_SIZE);
                long start = end - BATCH_SIZE + 1;
                // 批量生成 Number
                for (int i=0;i< BATCH_SIZE;i++){
                    tmp.add(start + i);
                }
            }
            // 从集合中获取 Number
            return tmp.remove(0);
        }
    }
}