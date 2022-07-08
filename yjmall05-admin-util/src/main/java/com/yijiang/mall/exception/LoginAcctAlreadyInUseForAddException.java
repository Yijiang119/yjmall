package com.yijiang.mall.exception;

/**
 * @ClassName LoginAcctAlreadyInUseException
 * @Description 保存或更新Admin时如果检测到登录账号重复抛出这个异常
 * @Author 姜泽昊
 * @Date 2022/3/21 18:42
 * @Version 1.0
 */
public class LoginAcctAlreadyInUseForAddException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LoginAcctAlreadyInUseForAddException() {
        super();
    }

    public LoginAcctAlreadyInUseForAddException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUseForAddException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUseForAddException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUseForAddException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
