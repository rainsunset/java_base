package com.ligw.javabase.lambda.bo;

import java.util.List;

/**
 * @Description: TODO
 * @Author: Amo
 * @CreateDate: 2021/1/30
 */
public class StudentBO {
    String name;

    int age;

    List<String> session;

    public StudentBO(String name, int age, List<String> session) {
        this.name = name;
        this.age = age;
        this.session = session;
    }

    @Override
    public String toString() {
        return "StudentBO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", session=" + session +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getSession() {
        return session;
    }

    public void setSession(List<String> session) {
        this.session = session;
    }
}
