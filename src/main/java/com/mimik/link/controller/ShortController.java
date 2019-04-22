package com.mimik.link.controller;

import com.mimik.link.flow.IShowrLinkFlow;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ShortController
 * @Description TODO
 * @Author liugang
 * @Date 2019/4/14 10:35
 * @Version
 */
@RestController
public class ShortController {

    @Autowired
    private IShowrLinkFlow showrLinkFlow;

    @PostMapping(value = "short-url")
    public String create(@RequestBody ShortUrlForm form) {
        return showrLinkFlow.shortUrl(form.getStrategy(), form.getUrl());
    }

    @Data
    static class ShortUrlForm{
        private String strategy;
        private String url;
    }
}
