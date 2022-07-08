package com.yijiang.mall.exception;

/**
 * @ClassName LoginAcctAlreadyInUseException
 * @Description 保存或更新Admin时如果检测到登录账号重复抛出这个异常
 * @Author 姜泽昊
 * @Date 2022/3/21 18:42
 * @Version 1.0
 */
public class LoginAcctAlreadyInUseForUpdateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LoginAcctAlreadyInUseForUpdateException() {
        super();
    }

    public LoginAcctAlreadyInUseForUpdateException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUseForUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUseForUpdateException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUseForUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
