package com.yijiang.mall.exception;

/**
 * @ClassName LoginFailedException
 * @Description 登录失败后抛出的异常
 * @Author 姜泽昊
 * @Date 2022/3/21 10:37
 * @Version 1.0
 */
public class LoginFailedException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
