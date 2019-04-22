package com.mimik.link.service;

import com.mimik.link.enums.NumberTypeEnum;

/**
 * @ClassName INumberGenerator
 * @Description TODO
 * @Author liugang
 * @Date 2019/4/14 10:24
 * @Version
 */
public interface INumberGenerator {

    /**
     * 生成自增key
     * @param type
     * @return
     */
    Long nextNumber(NumberTypeEnum type);


}
