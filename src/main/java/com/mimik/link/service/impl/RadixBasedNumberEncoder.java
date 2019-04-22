package com.mimik.link.service.impl;

import com.mimik.link.service.INumberEncoder;

public class RadixBasedNumberEncoder implements INumberEncoder {

    private final int radix;

    public RadixBasedNumberEncoder(int radix){
        this.radix = radix;
    }

    @Override
    public String encode(Long id) {
        return Long.toString(id, radix);
    }

    @Override
    public Long decode(String str) {
        return Long.valueOf(str, radix);
    }
}