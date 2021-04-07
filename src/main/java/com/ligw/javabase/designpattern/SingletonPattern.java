package com.ligw.javabase.designpattern;

/**
 * @Description: 尽可能多的单例模式
 * @Author: Amo
 * @CreateDate: 2020/9/9
 */
public class SingletonPattern {

    /**
     * 懒汉模式
     * 特点：需要时再创建
     * 缺点：需要的时候创建类实例,没有考虑到多线程，多线程环境下无法保证单例效果
     */
    static class Lanhan {
        private static Lanhan lanhan;
        public static Lanhan getlanhan() {
            if (null == lanhan) {
                lanhan = new Lanhan();
            }
            return lanhan;
        }
    }

    /**
     * 线程安全的懒汉模式
     * 要点： 通过加锁和二次检查来实现线程安全
     * 缺点：有时候会因为无法获取锁而返回null
     */
    static class SafeLanhan {
        private static SafeLanhan lanhan;

        // 1.5之前的问题：https://www.jianshu.com/p/769f2593c94e
        // 涉及关键词 volatile

        public static SafeLanhan getlanhan() {
            // 第一层检查
            if (null == lanhan) {
                // 同步锁
                synchronized (SafeLanhan.class) {
                    if (null == lanhan) {
                        // 第二曾检查
                        lanhan = new SafeLanhan();
                    }
                }
            }
            return lanhan;
        }
    }

    /**
     * 饿汉模式
     * 特点：先创建好 需要的时候就返回
     * 缺点：提前创建类实例,无论是否需要，只要类加载就进行了实例化，浪费资源.
     */
    static class Ehan {
        private Ehan() {}
        private static Ehan ehan = new Ehan();
        public static Ehan getEhan() {
            return ehan;
        }
    }

    /**
     * 用静态内部类方法的饿汉
     */
    static class InnerClass {
        private InnerClass() {
        }

        public static InnerClass getInstance() {
            return InnerClassSig.innerClass;
        }

        static class InnerClassSig {
            private static InnerClass innerClass = new InnerClass();
        }
    }

    /**
     * 使用枚举实现
     * 四种单例写法都需要额外的工作来实现序列化，否则每次反序列化一个序列化的对象时都会创建一个新的实例。且无法阻止通过反射强行调用私有构造函数。
     * 枚举类很好的解决了这两个问题，使用枚举除了线程安全和防止反射调用构造器之外，还提供了自动序列化机制，防止反序列化的时候创建新的对象。
     * 因为JVM会保证enum不能被反射并且构造器方法只执行一次，但枚举会很耗内存
     *
     */
    static class EnumSig{
        public static EnumSig getEnumSig() {
            return SigEnum.INSTANCE.getEnumSig();
        }

        static enum SigEnum{
            INSTANCE;
            private EnumSig enumSig;
            private EnumSig getEnumSig(){
                enumSig = new EnumSig();
                return enumSig;
            }
        }
    }
}
