package com.ligw.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: lambda表达式类型检查
 * @Author: Amo
 * @CreateDate: 2021/2/1
 */
public class TypeCheck {

    public static void main(String[] args) {
//        beanAndParamTypeGet();

        lambdaWithMethOverload();

    }

    public static void test(MyInta<String, List<String>> mia) {
        List<String> aaa = mia.addList("aaa", new ArrayList<>());
        System.out.println(aaa);
    }

    /**
     * lambda表达式类型及参数类型的推导
     */
    private static void beanAndParamTypeGet() {
        test(new MyInta<String, List<String>>() {
            @Override
            public List<String> addList(String str, List<String> list) {
                list.add(str);
                return list;
            }
        });

        test((str, list) -> {
            list.add(str);
            return list;
        });
    }


    public static void methodOverload1(MyIntb myIntb) {
        System.out.println("methodOverload1-MyIntb:" + myIntb.getName());
    }

    public static void methodOverload1(MyIntc myIntc) {
        System.out.println("methodOverload1-MyIntc:" + myIntc.getAge());
    }

    public static void methodOverload2(MyIntd myIntd) {
        System.out.println("methodOverload2-MyIntd" + myIntd.getName());
    }

    public static void methodOverload2(MyInte myInte) {
        System.out.println("methodOverload2-MyInte" + myInte.getAge());
    }

    /**
     * lambda表达式存在类型检查 会自动推搭配lambda表达式的类型
     * 遇到重载方法时 无法判定lambda表达式类型，语法检测不通过
     */
    private static void lambdaWithMethOverload() {
        methodOverload1(new MyIntb() {
            @Override
            public String getName() {
                System.out.println("In MyIntb by Override");
                return "name";
            }
        });

        methodOverload1(new MyIntc() {
            @Override
            public Integer getAge() {
                System.out.println("In MyIntc by Override");
                return 6;
            }
        });

        methodOverload1(() -> {
            System.out.println("In Unknown by lambda");
            return "name";
        });

        methodOverload1(() -> {
            System.out.println("In Unknown by lambda");
            return 6;
        });

        methodOverload2(new MyIntd() {
            @Override
            public Long getName() {
                System.out.println("In MyIntd by Override");
                return 77777L;
            }
        });

        methodOverload2(new MyInte() {
            @Override
            public Long getAge() {
                System.out.println("In MyInte by Override");
                return 66666L;
            }
        });

        // 报错 因为在
        //        methodOverload2(() -> {
        //            System.out.println("In Unknown by lambda");
        //            return 66666L;
        //        });
    }
}

@FunctionalInterface
interface MyInta<T, R> {
    R addList(T t, R r);
}

@FunctionalInterface
interface MyIntb {
    String getName();
}

@FunctionalInterface
interface MyIntc {
    Integer getAge();
}

@FunctionalInterface
interface MyIntd {
    Long getName();
}

@FunctionalInterface
interface MyInte {
    Long getAge();
}