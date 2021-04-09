package com.ligw.javabase.lang.clazz;

public class YangPerson {
    private String name;

    public static String COUNTRY = "CHINA";

    public static void saySomething() {
        System.out.println("YangPerson say 。。。。");
    }

    public static void main(String[] args) {
        System.out.println("in YangPerson main");
    }

    public YangPerson() {
        System.out.println("new YangPerson with no args Constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
