package com.social.media.util;

public class UnsupportedMethodException extends Exception {

    final String method;

    public UnsupportedMethodException(String method) {
        this.method = method;
    }

    @Override
    public String getMessage() {
        return String.format("This Url not support http method (%s)", method);
    }
}
