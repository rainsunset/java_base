package com.ligw.javabase.lang.clazz;

/**
 * @Description: TODO
 * @Author: Amo
 * @CreateDate: 2021/5/7
 */
public class InnerClazz {
    private int a;

    private static int sa;

    int b;

    static int sb;

    protected int c;

    protected static int sc;

    public int d;

    public static int sd;

    // 其他类想要访问(非静态)内部类的属性或方法，需要在外部类中创建内部类的对象作为一个属性，然后...
    ClazzB clazzB;
    // 其他类想要访问静态内部类的属性或方法可以直接new一个出来

    {
        // 外部类想要访问(非静态)内部类的变量或方法需要new一个内部类对象来访问
        ClazzB clazzB = new ClazzB();
        clazzB.ib = 1;

    }

    class ClazzB {
        // 普通内部类作为外部类的一个成员存在 访问外部类的方法和变量可以直接访问
        int isb = sb;
        int ib = b;

        {
            int imsb = sb;
        }
    }

    static class ClazzA {
        // 静态内部类相对于外部类是独立存在的，无法直接访问外部类的非静态变量和方法
        // int sib = b;
        // 必须使用new出来的对象来访问外部类的非静态方法和变量
        int sib = new InnerClazz().b;
        // 可以直接访问外部类的静态变量和方法
        int sisb = sb;

        {
            int sb = InnerClazz.sb;
            int b = new InnerClazz().b;
        }
    }

    // 局部(方法)内部类
    void methord1() {
        int ma = 1;
        class ParterInnerClass{
            // 局部内部类类可以直接访问外部类的成员，也可以直接访问方法内的成员变量
            void imeth() {
                System.out.println(ma + b);
            }
        }
        ParterInnerClass parterInnerClass = new ParterInnerClass();
        parterInnerClass.imeth();
    }

    // 匿名内部类
    // 前提有抽象类或者接口
    void methord2(){
        int i = 1;
        // new 应该是一个对象(这里是接口) 所以是匿名的对象。匿名内部类本质是匿名的对象???
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(i + b);
            }
        };
        runnable.run();
    }
}

class InnerClazzChild extends InnerClazz {
    {
        int sb = InnerClazz.sb;
    }

    public static void main(String[] args) {
        InnerClazzChild innerClazzChild = new InnerClazzChild();
        int b = innerClazzChild.b;
    }
}
