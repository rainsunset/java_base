package com.ligw.lambda;

import com.ligw.lambda.funint.IMessageFormat;
import com.ligw.lambda.funint.IUserCredential;
import com.ligw.lambda.funint.impl.MessageFormatImpl;
import com.ligw.lambda.funint.impl.UserCredentialImpl;

/**
 * @Description: 函数式接口验证
 * @Author: Amo
 * @CreateDate: 2021/1/17
 */
public class FunInt {
    public static void main(String[] args) {
        functionInterfaceComeIn();
    }

    /**
     * 函数式接口循序渐进化
     */
    private static void functionInterfaceComeIn() {
        IUserCredential uc = new UserCredentialImpl();
        System.out.println(uc.verify("admin"));
        // 调用默认方法
        System.out.println(uc.getCredential("admin"));
        // 调用静态方法
        String message = "hello world";
        if (IMessageFormat.verify(message)) {
            MessageFormatImpl messageFormat = new MessageFormatImpl();
            System.out.println(messageFormat.format(message));
        }

        // 匿名内部类 实现接口的抽象方法
        // 代码冗余明显->有意义的只有实现逻辑一句话
        IUserCredential ucInside = new IUserCredential() {
            @Override
            public String verify(String name) {
                return "admin".equals(name) ? "系统管理员" : "普通用户";
            }
        };
        System.out.println(ucInside.verify("admin"));

        // 用lambda实现内部类
        IUserCredential ucLambda = (String name) -> {
            return "admin".equals(name) ? "系统管理员" : "普通用户";
        };
        System.out.println(ucLambda.verify("admin"));
    }
}
