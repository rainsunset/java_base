package com.ligw.lambda;

/**
 * @Description: 用匿名内部类实现MCAD模式
 * @Author: Amo
 * @CreateDate: 2021/1/17
 */
public class McadByInsideClass {
    public static void main(String[] args) {
        // 传统模式下新线程的创建
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("inside-" + Thread.currentThread().getId());
            }
        },"inside").start();

        // jdk8新特性-lambda表达式创建新线程
        new Thread(() -> {
            System.out.println("lambda-" + Thread.currentThread().getId());
        },"lambda").start();
    }
}
