package com.ligw.javabase.first;

/**
 * @ClassName XiuShiFu
 * @Description: TODO
 * @Author: ligangwei
 * @CreateDate: 2021/5/10 16:41
 */
public class XiuShiFu {

    public static void main(String[] args) {

        // 在构造函数内对一个fifinal域的写入,与随后把这个被构造对象的引用赋值给一个引用变量,这两个操作之间不能重排序
        FinalExam a = new FinalExam();

        // 初次读一个包含fifinal域的对象的引用,与随后初次读这个fifinal域,这两个操作之间不能重排序
        System.out.println(a);
        System.out.println(a.a);
    }


}


class FinalExam {
    final int a = 10;

    public FinalExam() {
        System.out.println("a init");
    }
}