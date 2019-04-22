package com.mimik.link.support;

import com.mimik.link.ShortLinkApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @ClassName BaseTest
 * @Description classes 需要注入的类
 * @Author liugang
 * @Date 2019/4/17 17:45
 * @Version
 */

@SpringBootTest(classes = { ShortLinkApplication.class })
public class BaseTest extends AbstractTestNGSpringContextTests {

}
