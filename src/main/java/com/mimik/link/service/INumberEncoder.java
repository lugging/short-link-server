package com.mimik.link.service;

/**
 * @ClassName NumberEncoder
 * @Description 对Number 进行编解码，进一步减少key的长度
 * @Author liugang
 * @Date 2019/4/14 10:29
 * @Version
 */
public interface INumberEncoder {

    /**
     * 对 Number 进行编码
     * @param id
     * @return
     */
    String encode(Long id);

    /**
     * 对 Number 进行解码
     * @param str
     * @return
     */
    Long decode(String str);

}
