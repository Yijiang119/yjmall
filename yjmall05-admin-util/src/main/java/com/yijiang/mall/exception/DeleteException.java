package com.yijiang.mall.exception;

/**
 * @ClassName DeleteException
 * @Description 删除Admin时如果删除了自己就抛出这个异常
 * @Author 姜泽昊
 * @Date 2022/3/21 17:54
 * @Version 1.0
 */
public class DeleteException extends RuntimeException{

    public static final long serialVersionUID = 1L;

    public DeleteException() {
        super();
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    public DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
