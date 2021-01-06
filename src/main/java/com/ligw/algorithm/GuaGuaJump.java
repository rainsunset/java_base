package com.ligw.algorithm;

/**
 * @Description: 青蛙跳台算法
 * N阶台阶 每次跳1到2步，有几种跳法
 * @Author: Amo
 * @CreateDate: 2020/9/9
 */
public class GuaGuaJump {
    static int count = 0;

    public static int jumpOneByBatch(int n) {
        count++;
        if (n < 1) {
            return 0;
        }
        if (n < 4) {
            return n;
        }
        return jumpOneByBatch(n - 1) + jumpOneByBatch(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(jumpOneByBatch(13));
        System.out.println(count);
    }

}
