package com.ligw.javabase.lambda;

import java.util.function.*;

/**
 * @Description: 常见函数式接口
 * @Author: Amo
 * @CreateDate: 2021/1/30
 */
public class FunIntInJre {
    public static void main(String[] args) {
        javaUtilFunctionPrictice();
    }


    /**
     * java.util.function包内常用函数式接口练习
     */
    private static void javaUtilFunctionPrictice() {
        // java.util.function包提供了常用的函数式功能接口
        // 模拟实现身份验证 返回boolean
        Predicate<String> pre = (String name) -> {
            return "admin".equals(name);
        };
        System.out.println(pre.test("manager"));
        // 模拟实现消息发送 什么也不返回
        Consumer<String> con = (String message) -> {
            System.out.println("模拟发送消息:" + message);
        };
        con.accept("66666");
        // 接收类型T 返回类型R
        Function<String, Integer> fun = (String str) -> {
            return str.length();
        };
        System.out.println(fun.apply("妈咪妈咪哄"));
        // 不接收任何参数 提供T对象的创建工厂
        Supplier<Integer> sup = () -> {
            return (int) (Math.random() * 1000);
        };
        System.out.println(sup.get());
        // 接收参数对象T，返回T
        UnaryOperator<String> uo = (String message) -> {
            return message += "(length:" + message.length() + ")";
        };
        System.out.println(uo.apply("abocideefuge"));
        // 接收两个对象T,返回一个对象T
        BinaryOperator<Integer> bo = (Integer a, Integer b) -> {
            return a > b ? a : b;
        };
        System.out.println(bo.apply(2, 3));
    }
}
