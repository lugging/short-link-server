package com.mimik.link.flow;

/**
 * @ClassName IShowrLinkFlow
 * @Description TODO
 * @Author liugang
 * @Date 2019/4/14 10:48
 * @Version
 */
public interface IShowrLinkFlow {

    /**
     * 创建短链接
     * @param strategy
     * @param url
     * @return
     */
    String shortUrl(String strategy, String url);

    /**
     * 查询短链接
     * @param request
     * @return
     */
    String getTargetUrl(String request);

}
