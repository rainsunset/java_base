/**
 * Company
 * Copyright (C) 2004-2018 All Rights Reserved.
 */
package com.ligw.object;

/**
 * 进制转换 Hexadecimal conversion
 * @author ligw
 * @version $Id HexCovTest.java, v 0.1 2018-03-22 15:34 ligw Exp $$
 */
public class HexCovTest {

    public static void main(String[] args) {
        int a = 97; //Dec(十进制)

        //十进制 -> 二进制 Bin(二进制)

        String str = Integer.toBinaryString(a);
        if(str.length() < 32){
            str = 0 + str;
            String fuoosize = "00000000000000000000000000000000";
            str = fuoosize.substring(str.length()) + str;
        }
        System.out.println("10 -> 2：" + str);

        //十进制 -> 八进制 Oct(八进制)
        str = Integer.toOctalString(a);
        System.out.println("10 -> 8：" + str);

        //十进制 -> 十六进制 Hex(十六进制)
        str = Integer.toHexString(a);
        System.out.println("10 -> 16：" + str);

        //十进制 -> 特定进制
        int random = (int)(Math.random() * 10);
        str = Integer.toString(a, random);
        System.out.println("10 -> " + random + "：" + str);

        //十进制 -> 字符
        //大写英文字符对应十进制范围：65-90  小写英文字母对应十进制范围：97-122
        char c = (char) a;
        System.out.println("10 -> char："+c);
        char bigC = (char) (a - 32);
        System.out.println("10 -> char（bigsize）："+bigC);

        //二进制 -> 十进制
        str = "1010"; //补0
        a = Integer.parseInt(str, 2);
        System.out.println("2 -> 10：" + a);

        //八进制 -> 十进制
        str = "12";
        a = Integer.parseInt(str, 8); //str转为int后的值必须大于8
        System.out.println("8 -> 10：" + a);

        //十六进制 -> 十进制
        str = "a";
        a = Integer.parseInt(str, 16);
        System.out.println("16 -> 10：" + a);

    }
}
