package com.ligw.codeWrittrnTest;

/**
 * @Description: i++测试-验证内存模型
 * @Author: Amo
 * @CreateDate: 2020/9/8
 */
public class IjjTest {
    private void increase(int i, String s) {
        i = i + 1;
        s = "b";
    }

    private void increaseBoy(Boy boy) {
        int age = boy.getAge();
        boy.setAge(age+1);
        String name = boy.getName();
        boy.setName("b");
    }

    public static void main(String[] args) {
        IjjTest ijjTest = new IjjTest();
        int i = 0;
        String s = "s";
        ijjTest.increase(i, s);
        Boy boy = new Boy();
        boy.setAge(0);
        boy.setName("s");
        ijjTest.increaseBoy(boy);
        System.out.println(i + "," + s + "," + boy.getAge() + "," + boy.getName());
        i = i++;
        s = (s += "s");
        System.out.println(i+ "," + s);
        i++;
        s += "s";
        System.out.println(i+ "," + s);
    }

    static class Boy {
        int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
