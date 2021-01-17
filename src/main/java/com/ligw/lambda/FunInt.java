package com.ligw.lambda;

import com.ligw.lambda.funint.IUserCredential;
import com.ligw.lambda.funint.impl.UserCredentialimpl;

/**
 * @Description: 函数式接口验证
 * @Author: Amo
 * @CreateDate: 2021/1/17
 */
public class FunInt {
    public static void main(String[] args) {
        IUserCredential uc = new UserCredentialimpl();
        System.out.println(uc.verify("admin"));
    }
}
