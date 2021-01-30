package com.ligw.lambda;

import com.ligw.lambda.bo.StudentBO;

import java.util.ArrayList;
import java.util.List;

/**
 * lambda 表达式的基本语法
 * [接口声明] = (参数) -> {执行代码块}
 * 1) 声明 就是和lambda表达四绑定的接口类型
 * 2) 参数 包含在一堆圆括号中，和绑定的接口中的抽象方法中的参数个数及顺序一致
 * 3) 操作符 ->
 * 4) 执行代码块 包含在一堆打括号中，出现在操作符右侧
 */
public class Grammar {
    public static void main(String[] args) {

        ILambda1 i11 = () -> {
            System.out.println("lambdaTestTwoLine");
            System.out.println("lambdaTestTwoLine");
        };
        i11.test();
        // 当方法体只有一行时 可以省略大括号
        ILambda1 i12 = () -> System.out.println("lambdaTestOneLine");
        i12.test();

//        Ilambda2 i21 = (String pa, int pb, List<String> pc, StudentBO pd) -> {
        // 等同于一下写法，可在定义参数时不定义参数的类型，jvm在运行时会依据lambda表达式绑定的接口方法自动判定参数类型
        ILambda2 i21 = (pa, pb, pc, pd) -> {
            pa = pa + pa;
            System.out.println(pa);
            pb = pb + pb;
            System.out.println(pb);
//            pc.addAll(pc);
            pc = new ArrayList<>();
            pc.add("nc");
            System.out.println(pc);
//            pd.setName(pa);
//            pd.setAge(pb);
//            pd.setSession(pc);
            pd = new StudentBO("na", 12, pc);
            System.out.println(pd);
        };
        String a = "a";
        int b = 1;
        List<String> c = new ArrayList<>();
        c.add("c");
        StudentBO d = new StudentBO(a, b, c);
        i21.test(a, b, c, d);
        System.out.println("a:" + a + ";b:" + b+ ";c:" + c+ ";d:" + d);

        // 当方法体只有一行时 可以在省略大括号的基础上省略return关键字(单行执行的结果会自动返回)
        ILambda3 i31 = (x, y) -> {
            return x * y;
        };
        ILambda3 i32 = (x, y) -> x * y;
    }

    interface ILambda1 {
        void test();
    }

    interface ILambda2 {
        void test(String a, int b, List<String> c, StudentBO d);
    }

    interface ILambda3{
        int test(int x, int y);
    }
}
