package com.ligw.javabase.lang.clazz;

/**
 * @Description: TODO
 * @Author: Amo
 * @CreateDate: 2021/5/7
 */
public class InnerClazzGet {
    public static void main(String[] args) {
        InnerClazz innerClazz = new InnerClazz();
//        int a = innerClazz.a;
//        int sa = InnerClazz.sa;

        int b = innerClazz.b;
        int sb = InnerClazz.sb;
        int c = innerClazz.c;
        int sc = InnerClazz.sc;
        int d = innerClazz.d;
        int sd = InnerClazz.sd;

        InnerClazz.ClazzA clazzA = new InnerClazz.ClazzA();
    }
}
