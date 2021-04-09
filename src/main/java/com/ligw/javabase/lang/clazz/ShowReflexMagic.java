package com.ligw.javabase.lang.clazz;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ShowReflexMagic {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {

//        getClassObj();
//        whenLoadClassObj();
//        getConstructor();
//        getMethord();

//        getField();
        return;

    }

    private static void getField() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class studentClass = Student.class;
        Field[] fields = studentClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        // public java.lang.String com.ligw.javabase.lang.clazz.Student.school
        // public static java.lang.String com.ligw.javabase.lang.clazz.YangPerson.COUNTRY
        // As Well 也是只能拿到自己及父类被public修饰的属性

        Student student = (Student) studentClass.newInstance();

        Field school = studentClass.getField("school");
        System.out.println(school);
        // public java.lang.String com.ligw.javabase.lang.clazz.Student.school
        school.set(student, "985");
        System.out.println(student.school);
        // 985

        Field grade = studentClass.getDeclaredField("grade");
        System.out.println(grade);
        // private java.lang.String com.ligw.javabase.lang.clazz.Student.grade
        grade.setAccessible(true);
        grade.set(student, "八年级");
        System.out.println(student.getGrade());
        // 八年级

        // getDeclaredField是获取不到父类的私有属性的
        // Field name = studentClass.getDeclaredField("name");
        // Exception in thread "main" java.lang.NoSuchFieldException: name
    }

    private static void getMethord() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class studentClass = Student.class;
        Method[] methods = studentClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        // public static void com.ligw.javabase.lang.clazz.Student.main(java.lang.String[])
        // public java.lang.String com.ligw.javabase.lang.clazz.Student.getGrade()
        // public java.lang.String com.ligw.javabase.lang.clazz.YangPerson.getName()
        // public void com.ligw.javabase.lang.clazz.YangPerson.setName(java.lang.String)
        // public static void com.ligw.javabase.lang.clazz.YangPerson.saySomething()
        // public final void java.lang.Object.wait() throws java.lang.InterruptedException
        // public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
        // public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
        // public boolean java.lang.Object.equals(java.lang.Object)
        // public java.lang.String java.lang.Object.toString()
        // public native int java.lang.Object.hashCode()
        // public final native java.lang.Class java.lang.Object.getClass()
        // public final native void java.lang.Object.notify()
        // public final native void java.lang.Object.notifyAll()
        // getMethods仅能得到北public修饰的方法

        // 得到特定的方法-父类的公有方法
        Method saySomethingM = studentClass.getMethod("saySomething");
        System.out.println(saySomethingM);
        // public static void com.ligw.javabase.lang.clazz.YangPerson.saySomething()
        saySomethingM.invoke(studentClass.newInstance());
        // new YangPerson with no args Constructor
        // new Student by no arges Constructor
        // YangPerson say 。。。。

        // 得到特定的方法
        Method getGradeM = studentClass.getMethod("getGrade");
        System.out.println(getGradeM);
        // public java.lang.String com.ligw.javabase.lang.clazz.Student.getGrade()
        getGradeM.invoke(studentClass.newInstance());
        // new YangPerson with no args Constructor
        // new Student by no arges Constructor
        // in public methord getGrade

        // 得到私有的方法并调用
        Method getNameM = studentClass.getDeclaredMethod("setGrade", String.class);
        System.out.println(getNameM);
        // private void com.ligw.javabase.lang.clazz.Student.setGrade(java.lang.String)
        getNameM.setAccessible(true);
        getNameM.invoke(studentClass.newInstance(),"dida");
        // new YangPerson with no args Constructor
        // new Student by no arges Constructor
        // in private methord getGrade
    }

    /**
     * 获取字节码对象的构造器对象
     *
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static void getConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // Constructor
        Class studentClass = Student.class;

        // 获取构造器对象
        Constructor[] constructors = studentClass.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        // public com.ligw.javabase.lang.clazz.Student(java.lang.String,java.lang.String)
        // public com.ligw.javabase.lang.clazz.Student()
        // getConstructors()仅能得到public属性的Constructor，
        // 类默认有一个public无参构造方法；
        // 当类有一个有参构造方法时(不论是否是public)，默认的无参构造方法就没有了，需要自己实现无参构造方法。

        // 获取指定的构造器 并用获取的构造器创建类的实例
        Constructor constructor1 = studentClass.getConstructor();
        System.out.println(constructor1);
        // public com.ligw.javabase.lang.clazz.Student()
        constructor1.newInstance();
        // new YangPerson with no args Constructor
        // new Student by no arges Constructor

        Constructor constructor2 = studentClass.getConstructor(String.class, String.class);
        System.out.println(constructor2);
        // public com.ligw.javabase.lang.clazz.Student(java.lang.String,java.lang.String)
        constructor2.newInstance("lucy", "211大学");
        // new YangPerson with no args Constructor
        // new Student by full arges Constructor

        // 直接用getConstructor获取私有的构造器会报错，要用getDeclaredConstructor获取私有的构造器
        // Constructor constructor3 = studentClass.getConstructor(String.class);
        // Exception in thread "main" java.lang.NoSuchMethodException: com.ligw.javabase.lang.clazz.Student.<init>(java.lang.String)

        // getDeclaredConstructor可以获取所有的构造器
        Constructor constructor4 = studentClass.getDeclaredConstructor(String.class);
        System.out.println(constructor4);
        // private com.ligw.javabase.lang.clazz.Student(java.lang.String)
        // 虽然获取到了私有的构造器 但是直接用私有的构造器获取实例汇报没有权限的错误 要修改权限为true
        // constructor4.newInstance("linda");
        // Exception in thread "main" java.lang.IllegalAccessException: Class com.ligw.javabase.lang.clazz.ShowReflexMagic can not access a member of class com.ligw.javabase.lang.clazz.Student with modifiers "priv

        constructor4.setAccessible(true);
        constructor4.newInstance("linda");
        // new YangPerson with no args Constructor
        // new Student by sinagle arges Constructor

        // 便捷的写法 Class的newInstance()是没有参数的，！！对应的类必须要有public修饰的无参的构造方法
        studentClass.newInstance();
    }

    /**
     * 字节码文件的加载时机(加载再一次)
     *
     */
    private static void whenLoadClassObj() {
        // 1. (第一次)new 一个类的对象；
        YangPerson yangPerson = new YangPerson();
        // 2. 访问类的静态成员；
        System.out.println(YangPerson.COUNTRY);
        // 3. 调用类的静态方法；
        YangPerson.saySomething();
        // 4. 通过反射的方式创建一个类的字节码对象；
        Class yangPersonClass = YangPerson.class;
        // 5. new 一个子类的子类的对象；
        Student student = new Student();
        // 6. java命令执行一个字节码文件(java com.ligw.javabase.lang.clazz.YangPerson)；
    }

    /**
     * 获取字节码对象的三种方式
     *
     * @throws ClassNotFoundException
     */
    private static void getClassObj() throws ClassNotFoundException {
        // 1、类对象的getClass()方法
        Student student1 = new Student();
        Student student2 = new Student();
        System.out.println(student1 == student2);
        // false ==运算符比较的是两个对象的存地址
        Class studentClass11 = student1.getClass();
        Class studentClass12 = student2.getClass();
        System.out.println(studentClass11 == studentClass12);
        // true 创建类对象的时候是一句对象的字节码文件对象(Student.class对应的对象Class<Student>)来创建的，
        // 一个类的字节码对象JVM仅加载一次,仅有一份

        // 2、类型.class (基础类型只能用这种方法获取对应的字节码对象)
        Class integerClass1 = int.class;
        Class integerClass2 = Integer.class;
        System.out.println(integerClass1 == integerClass2);
        // false int跟Integer不是一种类型，int是基础数据类型，Integet是引用数据类型
        Class studentClass21 = Student.class;
        System.out.println(studentClass11 == studentClass21);
        // true 不解释了

        Class studentClass3 = Class.forName("com.ligw.javabase.lang.clazz.Student");
    }

}
