package com.ligw.javabase.lambda.funint;

/**
 * @Description: 消息传输格式化接口-只提供一个接口方法
 * @Author: Amo
 * @CreateDate: 2021/1/17
 */
@FunctionalInterface
public interface IMessageFormat {
    /**
     * 格式化消息
     *
     * @param message
     * @return
     */
    String format(String message);

    // 静态方法

    /**
     * 静态方法
     *
     * @param message
     * @return
     */
    static boolean verify(String message) {
        if (null == message || "".equals(message)) {
            return false;
        }
        return true;
    }
}
