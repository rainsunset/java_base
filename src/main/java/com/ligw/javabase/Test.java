package com.ligw.javabase;

public class Test {

    public void numChange (int a){
        a = a+10;
    }

    public static int b = 6;

    public static void main(String[] args) {
        Test test = new Test();
        int a = 6;
        test.numChange(a);
        System.out.println(a);
        test.numChange(Test.b);
        System.out.println(Test.b);
    }

}
