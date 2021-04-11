package com.ligw.javabase.lambda.funint.impl;

import com.ligw.javabase.lambda.funint.IMessageFormat;

public class MessageFormatImpl implements IMessageFormat {

    @Override
    public String format(String message) {
        return message + "(length:" + message.length() + ")";
    }
}
