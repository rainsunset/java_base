package com.ligw.lambda;

import com.ligw.lambda.funint.IMessageFormat;
import com.ligw.lambda.funint.IUserCredential;
import com.ligw.lambda.funint.impl.MessageFormatImpl;
import com.ligw.lambda.funint.impl.UserCredentialImpl;

import java.util.function.*;

/**
 * @Description: 函数式接口验证
 * @Author: Amo
 * @CreateDate: 2021/1/17
 */
public class FunInt {
    public static void main(String[] args) {
//        functionInterfaceComeIn();
//        javaUtilFunctionPrictice();

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

    /**
     * 函数式接口循序渐进化
     */
    private static void functionInterfaceComeIn() {
        IUserCredential uc = new UserCredentialImpl();
        System.out.println(uc.verify("admin"));
        // 调用默认方法
        System.out.println(uc.getCredential("admin"));
        // 调用静态方法
        String message = "hello world";
        if (IMessageFormat.verify(message)) {
            MessageFormatImpl messageFormat = new MessageFormatImpl();
            System.out.println(messageFormat.format(message));
        }

        // 匿名内部类 实现接口的抽象方法
        // 代码冗余明显->有意义的只有实现逻辑一句话
        IUserCredential ucInside = new IUserCredential() {
            @Override
            public String verify(String name) {
                return "admin".equals(name) ? "系统管理员" : "普通用户";
            }
        };
        System.out.println(ucInside.verify("admin"));

        // 用lambda实现内部类
        IUserCredential ucLambda = (String name) -> {
            return "admin".equals(name) ? "系统管理员" : "普通用户";
        };
        System.out.println(ucLambda.verify("admin"));
    }
}
