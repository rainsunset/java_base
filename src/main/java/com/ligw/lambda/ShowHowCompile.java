package com.ligw.lambda;

public class ShowHowCompile {

    public static void main(String[] args) {
        MyLambda ml = (message) -> System.out.println(message);
        ml.makeUp("hello lambda");
    }
    interface MyLambda{
        void makeUp(String msg);
    }

    /**

     编译ShowHowCompile.java： javac ShowHowCompile.java
     编译生成字节码文件 ShowHowCompile.class 和 ShowHowCompile$MyLambda.class

     反编译生成的ShowHowCompile.class字节码文件： javap -p ShowHowCompile.class
     控制台输出：
     Compiled from "ShowHowCompile.java"
     public class ShowHowCompile {
     public ShowHowCompile();
     public static void main(java.lang.String[]);
     private static void lambda$main$0(java.lang.String);
     }

     查看ShowHowCompile的执行过程： java -Djdk.internal.lambda.dumpProxyClasses ShowHowCompile
     执行之后又生成了文件：ShowHowCompile$$Lambda$1.class
     同时控制台输出: hello lambda

     反编译生成的ShowHowCompile$$Lambda$1.class字节码文件： javap -p ShowHowCompile$$Lambda$1.class
     控制台输出：
     final class ShowHowCompile$$Lambda$1 implements ShowHowCompile$MyLambda {
     private ShowHowCompile$$Lambda$1();
     public void makeUp(java.lang.String);
     }

     >> 可见在编译过程中先生成了一个静态的方法lambda$main$0，里面是对业务的实现
     >> 又在构建过程中生成了一个内部类型ShowHowCompile$$Lambda$1实现接口，在接口实现方法里调用了ShowHowCompile的lambda$main$0方法
     >> 最后在执行的时候 是new一个ShowHowCompile$$Lambda$1对象，把参数传进来，调用这个对象的makeUp方法
     */
}
