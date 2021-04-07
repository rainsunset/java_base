/**
 * Company
 * Copyright (C) 2004-2018 All Rights Reserved.
 */
package com.ligw.javabase.lang;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author ligw 数组测试类
 * @version $Id ListTest.java, v 0.1 2018-03-23 0:43 ligw Exp $$
 */
public class ListTest {

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();

        LinkedList linkedList = new LinkedList();
    }

    //数组复制速率
    //System.arraycopy() > clone > Arrays.copyOf > for循环
    //System.arraycopy()是native方法 也就是本地方法 最快
    //Arrays.copyOf 在源码中是调用System.arraycopy()的


    //遍历

    //索引

    //
}
