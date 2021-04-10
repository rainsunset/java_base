package com.ligw.javabase.designpattern.proxy.staticproxy;

public class PrintParamProxy implements DaoI {
    private DaoI daoI;

    public PrintParamProxy(DaoI daoI) {
        this.daoI = daoI;
    }

    @Override
    public void query(String args) {
        System.out.println("print arges:" + args);
        daoI.query(args);
    }
}
