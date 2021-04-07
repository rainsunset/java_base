/**
 * Company
 * Copyright (C) 2004-2018 All Rights Reserved.
 */
package com.ligw.javabase.algorithm;

/**
 * @author ligw
 * @version $Id CodeWrittrnTest.java, v 0.1 2018-03-26 11:59 ligw Exp $$
 */
public class StringWrittrnTest {

    /**
     * 一个字符串包含数字和字母，数字不变，字母反转。
     * 
     * @param str
     * @return
     */
    public static String reverLetter(String str) {
        // 记录下标
        int[] mark = new int[str.length()];
        // 字符或字母长度
        int letterLength = 0;
        for (int i = 0;i < str.length();i++) {
            int num = (int) str.charAt(i);
            // 判断是否为字母或其他符号
            if (48 > num || 57 < num) {
                // 字母 记录下标
                mark[letterLength] = i;
                // 记录字母个数
                letterLength++;
            }
        }
        char[] strArray = str.toCharArray();
        // 反转
        for (int i = 0;i < letterLength / 2;i++) {
            char temp = strArray[mark[i]];
            char temp2 = strArray[mark[letterLength - 1 - i]];
            strArray[mark[i]] = temp2;
            strArray[mark[letterLength - 1 - i]] = temp;
        }
        return new String(strArray);
    }
}
