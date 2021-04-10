package com.ligw.javabase.designpattern.proxy;

import com.ligw.javabase.designpattern.proxy.staticproxy.DaoI;

public class Daoa implements DaoI {
    @Override
    public void query(String args) {
        System.out.println("Daoa query by:" + args);
    }
}
