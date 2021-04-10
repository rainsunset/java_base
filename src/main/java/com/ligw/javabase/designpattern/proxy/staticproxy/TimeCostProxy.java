package com.ligw.javabase.designpattern.proxy.staticproxy;

public class TimeCostProxy implements DaoI{
    private DaoI daoI;

    public TimeCostProxy(DaoI daoI) {
        this.daoI = daoI;
    }

    @Override
    public void query(String args) {
        long current = System.currentTimeMillis();
        daoI.query(args);
        System.out.println("print cost time:" + String.valueOf(System.currentTimeMillis() - current));
    }
}