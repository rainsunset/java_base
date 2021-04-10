package com.ligw.javabase.designpattern.proxy.staticproxy;

import com.ligw.javabase.designpattern.proxy.Daob;

public class DaobProxyByExt extends Daob {
    @Override
    public void query(String args) {
        long current = System.currentTimeMillis();
        super.query(args);
        System.out.println("print cost time:" + String.valueOf(System.currentTimeMillis() - current));
    }
}
