package com.ligw.lambda.funint.impl;

import com.ligw.lambda.funint.IMessageFormat;

/**
 * @Description: TODO
 * @Author: Amo
 * @CreateDate: 2021/1/19
 */
public class MessageFormatImpl implements IMessageFormat {

    @Override
    public String format(String message) {
        return message + "(length:" + message.length() + ")";
    }
}
