package com.ligw.javabase.lambda.funint;

/**
 * @Description: 用户身份认证接口，只包含一个接口方法
 * 添加验证注解 @FunctionalInterface
 * @Author: Amo
 * @CreateDate: 2021/1/17
 */
@FunctionalInterface
public interface IUserCredential {

    /**
     * 原始需求
     * 验证用户身份
     * @param name 用户名
     * @return 用户角色
     */
    String verify(String name);

// 再添加其他方法 验证注解@FunctionalInterface会提示报错
//    boolean test();

    // jdk1.8中 接口除了包含抽象方法 还包含默认方法和静态方法
    // 如果想扩展接口的公共的功能方法 可以通过再接口中定义默认方法的方式实现
    // 默认方法的使用场景时给所有实现接口的对象添加了通用的方法

    /**
     * 默认方法
     *
     * @param name
     * @return
     */
    default String getCredential(String name) {
        // 模拟方法
        if ("admin".equals(name)) {
            return name + "：系统管理员";
        }
        return name + "：普通用户";
    }

    // java中所有的类型/对象都简介或直接继承自Object,所以从Object继承来的方法，即使是抽象的，也不影响函数式接口的语法语义
    @Override
    String toString();

}
