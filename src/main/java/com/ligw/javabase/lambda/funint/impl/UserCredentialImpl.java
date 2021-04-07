package com.ligw.javabase.lambda.funint.impl;

import com.ligw.javabase.lambda.funint.IUserCredential;

/**
 * @Description: 用户验证接口实现
 * @Author: Amo
 * @CreateDate: 2021/1/17
 */
public class UserCredentialImpl implements IUserCredential {

    @Override
    public String verify(String name) {
        if ("admin".equals(name)) {
            return "系统管理员";
        }
        return "普通用户";
    }
}
