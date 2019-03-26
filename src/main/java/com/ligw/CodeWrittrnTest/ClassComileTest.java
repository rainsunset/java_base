/**
 * Company
 * Copyright (C) 2004-2018 All Rights Reserved.
 */
package com.ligw.CodeWrittrnTest;

/**
 * java类编译过程测试
 * @author ligw
 * @version $Id ClassComileTest.java, v 0.1 2018-03-28 23:23 ligw Exp $$
 */
public class ClassComileTest {

    public static void main(String[] args) {
        new TestB();
        System.out.println("-----------------");
        new TestA();

//        执行结果
//        Teat A static
//        Test B static
//        in TestA
//        creat testA
//        in TestB
//        creat TestB
//        -----------------
//        in TestA
//        creat testA
        /**
         * 类的加载顺序
         * 0)父类静态对象和静态代码块
         * 1)子类静态对象和静态代码块
         * 2)父类非静态对象和非静态代码块
         * 3)父类构造函数
         * 4)子类非静态对象和非静态发代码块
         * 5)子类构造函数
         * 且0) 和 1)不需要调用new实例时候就会执行
         *
         */

    }
}

class TestA {
    public  TestA(){
        System.out.println("creat testA");

    }

    {
        System.out.println("in TestA");

    }

    static{
        System.out.println("TestA A static");
    }
}

class TestB extends TestA{
    public  TestB(){
        System.out.println("creat TestB");

    }

    {
        System.out.println("in TestB");

    }

    static {
        System.out.println("Test B static");
    }
}
