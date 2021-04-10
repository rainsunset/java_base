package com.ligw.javabase.designpattern.proxy.staticproxy;

import com.ligw.javabase.designpattern.proxy.Daoa;

public class DaoaProxyByExt extends Daoa {
    @Override
    public void query(String args) {
        System.out.println("print arges:" + args);
        long current = System.currentTimeMillis();
        super.query(args);
        System.out.println("print cost time:" + String.valueOf(System.currentTimeMillis() - current));
    }
}
