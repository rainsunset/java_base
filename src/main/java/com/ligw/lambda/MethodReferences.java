package com.ligw.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: 方法引用
 * @Author: Amo
 * @CreateDate: 2021/2/4
 */
public class MethodReferences {

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("kangkang", "男", 18));
        personList.add(new Person("Lili", "女", 17));

        // 匿名内部类实现 - 与业务无关代码太多
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        // lambda实现
        Collections.sort(personList,(p1,p2) -> {
            return p1.getAge() - p2.getAge();
        });

        // 静态方法引用
        Collections.sort(personList, Person::campareByAge);

        // 实例方法引用
        PersonUtil pu = new PersonUtil();
        Collections.sort(personList, pu::campareByName);

        // 构造方法引用
        IPerson ip = Person::new;
        ip.initPerson("macal", "男", 26);
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person{
    private String name;
    private String gender;
    private int age;

    public static int campareByAge(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }
}

class PersonUtil{
    public int campareByName(Person p1, Person p2) {
        return p1.getName().hashCode() - p2.getName().hashCode();
    }
}

interface IPerson{
    /**
     * 抽象方法：通过指定类型的构造方法初始化对象数据
     *
     * @param name
     * @param gender
     * @param age
     * @return
     */
    Person initPerson(String name, String gender, int age);
}
