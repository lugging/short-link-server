package com.mimik.link.repository;

import com.mimik.link.domain.TargetUrl;
import com.mimik.link.support.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

/**
 * @ClassName RepositoryTests
 * @Description TODO
 * @Author liugang
 * @Date 2019/4/14 21:53
 * @Version
 */

public class RepositoryTest extends BaseTest {

    @Autowired
    private TargetUrlRepository repository;

    @Test
    public void save(){
        TargetUrl targetUrl = TargetUrl.builder()
                .url("https://www.baidu.com")
                .id((long)Integer.MAX_VALUE)
                .status(0)
                .build();
        TargetUrl t = repository.save(targetUrl);
        Assert.assertNotNull(t);
    }


    @Test
    public void testFind() throws Exception {
        Optional<TargetUrl> targetUrl = repository.findById((long)Integer.MAX_VALUE);
        System.out.println( targetUrl.get().getUrl());
        Assert.assertTrue(targetUrl.isPresent());
    }
}
