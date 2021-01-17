package com.ligw.lambda.funint;

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

    default String getCredential(String name) {
        // 模拟方法
        if ("admin".equals(name)) {
            return name + "系统管理员";
        }
        return name + "普通用户";
    }

}
