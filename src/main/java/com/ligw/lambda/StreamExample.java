package com.ligw.lambda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Author: Amo
 * @CreateDate: 2021/3/26
 */
public class StreamExample {
    public static void main(String[] args) {
//        streamFilterTest();
    }

    private static void streamFilterTest() {
        List<String> accountList = new ArrayList<>();
        accountList.add("lulcy");
        accountList.add("lili");
        accountList.add("pusuli");

        // 需求： 筛选出账号长度大于等于5的账号
        int minValidLength = 5;

        // for
        for (String account : accountList) {
            if (minValidLength <= account.length()) {
                System.out.println(account);
            }
        }

        // iter
        Iterator<String> it = accountList.iterator();
        while (it.hasNext()) {
            String account = it.next();
            if (minValidLength <= account.length()) {
                System.out.println(account);
            }
        }

        // streasm
        List<String> validAccountList = accountList.stream().filter(account -> minValidLength >= account.length())
                .collect(Collectors.toList());
        System.out.println(validAccountList);
    }
}
