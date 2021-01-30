package com.ligw.lambda;

/**
 * @Description: 匿名内部类和lambda变量捕获对比
 * 变量捕获：对于所属作用域的变量访问规则
 * @Author: Amo
 * @CreateDate: 2021/1/30
 */
public class VarAccess {
    private String s1 = "全局变量";

    public static void main(String[] args) {
        VarAccess innerClassVarAccess = new VarAccess();
        innerClassVarAccess.innerClassVar();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main-innerClass:"+ innerClassVarAccess.s1);

        VarAccess lambdaVarAccess = new VarAccess();
        lambdaVarAccess.lambdaVar();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main-lambda"+ lambdaVarAccess.s1);
    }

    public void innerClassVar() {
        String s2 = "局部变量";
        new Thread(
            new Runnable() {
                String s3 = "内部变量";
                @Override
                public void run() {
                    // 可直接访问全局变量 内部类的this关键字表示的是当前内部类的对象，所以不能用this获取全局变量
                    System.out.println(s1);
                    s1 = "innerClass修改全局变量";
                    System.out.println(s1);

                    // 可直接访问局部变量
                    System.out.println(s2);
                    // 但是不能修改值(因为局部变量对匿名内部类来说是finall类型得，不允许修改)
//                    s2 = "innerClass修改局部变量";

                    // 获取内部变量可直接获取或用this关键字获取
                    System.out.println(s3);
                    System.out.println(this.s3);
                    // 当然也是可以改变内部变量的值的
                    s3 += "直接修改";
                    System.out.println(s3);
                }
            }
        ).start();
    }

    public void lambdaVar() {
        String s2 = "lambda局部变量";
        new Thread(() -> {
            String s3 = "lambda内部变量";
            // 可直接访问全局变量
            System.out.println(s1);
            // 也可用this关键字访问全局变量
            System.out.println(this.s1);
            // 当然也是可以直接修改的
            s1 = "lambda修改全局变量";
            System.out.println(s1);

            // 可直接访问内部变量
            System.out.println(s2);
            // 同样不能修改内部变量，默认推导变量得修饰符伟final
//            s2 = "lambda修改局部变量";

            // 可直接访问内部变量
            System.out.println(s3);
            s3 += "直接修改";
            System.out.println(s3);
            // lambda表达式中的变量操作相对匿名内部类优化了this关键字的作用域，不再单独建立对象作用域，
            // 表达式本社呢就是所属对象本身的一部分
        }
        ).start();
    }
}