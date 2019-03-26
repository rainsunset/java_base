/**
 * Company
 * Copyright (C) 2004-2018 All Rights Reserved.
 */
package com.ligw.lang;

/**
 * @author ligw
 * @version $Id TestObject.java, v 0.1 2018-02-28 19:13 ligw Exp $$
 */
public class IntegerTest {

    public String str;

    public Integer num;

    public int size;

    public IntegerTest(String str,Integer num,int size){
        this.str = str;
        this.num = num;
        this.size = size;
    }

    public static void main(String[] args) {
        Integer a = new Integer(200);
        Integer b = new Integer(200);
        Integer c = 200;
        Integer e = 200;
        int d = 200;

        System.out.println("两个new出来的对象    ==判断"+(a==b));
        System.out.println("两个new出来的对象    equal判断"+a.equals(b));
        System.out.println("new出的对象和用int赋值的Integer   ==判断"+(a==c));
        System.out.println("new出的对象和用int赋值的Integer   equal判断"+(a.equals(c)));
        System.out.println("两个用int赋值的Integer    ==判断"+(c==e));
        System.out.println("两个用int赋值的Integer    equal判断"+(c.equals(e)));
        System.out.println("基本类型和new出的对象   ==判断"+(d==a));
        System.out.println("基本类型和new出的对象   equal判断"+(a.equals(d)));
        System.out.println("基本类型和自动装箱的对象   ==判断"+(d==c));
        System.out.println("基本类型和自动装箱的对象   equal判断"+(c.equals(d)));

        Integer ia = 1000;//自动装箱
        int ib = new Integer(1); //自动拆箱 ib = .intVal()

        //Integer 中 [-128-127] 是作为基本数据类型处理(自动拆箱)的 - 为了提高效率
        Integer ioa = 127;
        Integer iob = 127;
        System.out.println(ioa == iob);

        int biga = 666;
        int bigb = 666;
        System.out.println("bigb == bigb:" + (biga == bigb));



    }

}
