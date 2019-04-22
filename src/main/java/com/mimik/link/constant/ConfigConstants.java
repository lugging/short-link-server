package com.mimik.link.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConfigConstants
 * @Description TODO
 * @Author liugang
 * @Date 2019/4/14 10:50
 * @Version
 */
@Data
@Component
public class ConfigConstants {

    @Value("${base_doman:mimik}")
    private String baseDomain;
}
