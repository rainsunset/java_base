package com.ligw.javabase.designpattern.proxy;

import com.ligw.javabase.designpattern.proxy.staticproxy.DaoI;

public class Daob implements DaoI {
    @Override
    public void query(String args) {
        System.out.println("Daob query by:" + args);
    }
}
