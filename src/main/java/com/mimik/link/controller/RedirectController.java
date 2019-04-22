package com.mimik.link.controller;

import com.mimik.link.flow.IShowrLinkFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.result.view.Rendering;

/**
 * @ClassName RedirectController
 * @Description TODO
 * @Author liugang
 * @Date 2019/4/14 19:37
 * @Version
 */

@Controller
public class RedirectController {

    @Autowired
    private IShowrLinkFlow showrLinkFlow;

    @GetMapping("s/{code}")
    public Rendering redirect(@PathVariable String code){
        String url = getTargetUrl(code);
        // 使用 RedirectView，进行请求重定向
        return Rendering.redirectTo(url).build();
    }

    private String getTargetUrl(String code) {
        return this.showrLinkFlow.getTargetUrl(code);
    }

//
//    @RequestMapping("{code}")
//    public void redirect(@PathVariable String code, HttpServletResponse response) throws IOException {
//        String url = getTargetUrl(code);
//        // 调用 sendRedirect 方法，进行请求重定向
//        response.sendRedirect(url);
//    }
}
