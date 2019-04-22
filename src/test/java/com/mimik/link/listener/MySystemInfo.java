package com.mimik.link.listener;

import com.google.common.collect.Maps;
import com.vimalselvam.testng.SystemInfo;

import java.util.Map;

/**
 * @ClassName MySystemInfo
 * @Description TODO
 * @Author liugang
 * @Date 2019/4/17 18:12
 * @Version
 */
public class MySystemInfo implements SystemInfo {
    @Override
    public Map<String, String> getSystemInfo() {
        Map<String, String> stringMap =  Maps.newHashMap();
        stringMap.put("TestEnv", "DEV");
        stringMap.put("项目", "ERS");
        stringMap.put("version", "V1.1.0");
        return stringMap;
    }
}
