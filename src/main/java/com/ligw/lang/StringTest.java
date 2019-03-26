/**
 * Company
 * Copyright (C) 2004-2018 All Rights Reserved.
 */
package com.ligw.lang;

import java.util.Date;
import java.util.Iterator;

/**
 * @author ligw
 * @version $Id StringTest.java, v 0.1 2018-03-06 19:47 ligw Exp $$
 */
public class StringTest {

    public static void main(String[] args) {
        // String 不可变字符序列
        String str1 = new String("abcd");
        String str2 = new String("abcd");
        String str3 = "abc";
        String str4 = "abc";
        //内存分析 -
        System.out.println("String Object >> " + (str1 == str2));
        System.out.println("String BaseData >> " + (str3 == str4));
        //获取文件格式
        String fileName = "lesson1.mp4";
        System.out.println(fileName.lastIndexOf("."));
        //substring 包前不包后
        System.out.println(fileName.substring(fileName.lastIndexOf(".") + 1));
        String str5 = "  你好！这里是中国，你 哪个 绺子的? ";
        System.out.println(str5.charAt(2));
        System.out.println("trim前：" + str5.length() + "trim后：" + str5.trim().length());
        String[] strSp = str5.split(" ");
        for (String str : strSp) {
            System.out.println(str);
        }
        // 字符串格式化
        System.out.println(String.format("你好，我是%s,今年%d岁了，长度%f，现在的时间是%tF，我敢说在场的%d%%都是傻子，对么？%b", "你大爷", 15, 18.8, new Date(), 25, true));
        //
        System.out.println(str1.compareTo(str2));


        // StringBuilder 可变字符序列 线程不安全
        // 默认初始化 长度为16的字符串数组
        StringBuilder stringBuilder1 = new StringBuilder();
        // 初始化长度为32的字符串数组
        StringBuilder stringBuilder2 = new StringBuilder(32);
        // 初始化长度为字符长度+16的字符串数组
        StringBuilder stringBuilder3 = new StringBuilder("abcd你好");
        stringBuilder3.append("吃藕啊").append(55555).append(true); //return this实现方法链
        System.out.println(stringBuilder3);

        for (int i = 65;i < 122 ; i++) {
            //当StringBuilder的初始化字符数组长度不够用时 会copy内容到新数组 新数组长度为老数组长度*2+2
            stringBuilder2.append((char)i);
        }
        System.out.println(stringBuilder2);
        //删除 包头不包尾
        stringBuilder2.delete(3, 5);
        System.out.println("delete(3, 5) -> :" + stringBuilder2);
        //插入 插入下标后
        stringBuilder2.insert(3,"DE");
        System.out.println("insert(3,\"DE\") -> :"+stringBuilder2);
        //反转
        stringBuilder2.reverse();
        System.out.println("reverse() -> ：" + stringBuilder2);

        // StringBuffer 可变字符序列 线程安全
        StringBuffer stringBuffer;

    }
}
