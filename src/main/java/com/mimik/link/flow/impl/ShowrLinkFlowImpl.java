package com.mimik.link.flow.impl;

import com.mimik.link.constant.ConfigConstants;
import com.mimik.link.domain.TargetUrl;
import com.mimik.link.enums.NumberTypeEnum;
import com.mimik.link.flow.IShowrLinkFlow;
import com.mimik.link.repository.TargetUrlRepository;
import com.mimik.link.service.INumberEncoder;
import com.mimik.link.service.INumberGenerator;
import com.mimik.link.service.RequestEncoder;
import com.mimik.link.service.impl.RadixBasedNumberEncoder;
import com.mimik.link.service.impl.RedisBasedBatchNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName ShowrLinkFlowImpl
 * @Description TODO
 * @Author liugang
 * @Date 2019/4/14 10:49
 * @Version
 */
@Service
public class ShowrLinkFlowImpl implements IShowrLinkFlow {

    private INumberEncoder numberEncoder = new RadixBasedNumberEncoder(35);

    @Autowired
    private ConfigConstants constants;

    @Autowired
    private RequestEncoder requestEncoder;

    @Autowired
    private RedisBasedBatchNumberGenerator numberGenerator;

    @Autowired
    TargetUrlRepository targetUrlRepository;

    @Override
    public String shortUrl(String strategy, String url) {
        // 生成 Number Key
        Long key = numberGenerator.nextNumber(NumberTypeEnum.TINY_URL);
        // 构建并持久化 Target Url
        TargetUrl targetUrl = TargetUrl.builder()
                .id(key)
                .url(url)
                .build();

        targetUrlRepository.save(targetUrl);

        // 对 key 进行编码，获得更短的 code
        String code = numberEncoder.encode(key);

        // 对策略和code 进行再次编码
        String request = requestEncoder.encode(strategy, code);
        // 与短链域名进行拼接，获得最终的短链接
        return String.format("%s%s%s", constants.getBaseDomain(), "/", request);
    }

    @Override
    public String getTargetUrl(String request) {
        RequestEncoder.StrategyAndCode strategyAndCode = this.requestEncoder.decode(request);

        // 对 Code 进行解密，获得 Key
        Long key = numberEncoder.decode(strategyAndCode.getCode());

        // 从存储中获取 Target Url
        Optional<TargetUrl> targetUrl = targetUrlRepository.findById(key);

        // 返回目标 URL 地址
        return targetUrl.isPresent() ? targetUrl.get().getUrl() : null;
    }
}
