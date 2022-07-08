package com.yijiang.mall.util;

/**
 * @ClassName ResultEntity
 * @Description 用于统一项目中所有Ajax请求的返回值类型(未来也可用于分布式架构各个模块间调用时返回统一类型)
 * @Author 姜泽昊
 * @Date 2022/3/20 15:25
 * @Version 1.0
 */
public class ResultEntity<T> {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String NO_MESSAGE = "NO_MESSAGE";
    public static final String NO_DATA = "NO_DATA";
    /**
     * 返回操作结果为成功，不带数据
     * @return
     */
    public static <E> ResultEntity<E> successWithoutData() {
        return new ResultEntity<E>(SUCCESS, NO_MESSAGE, null);
    }
    /**
     * 返回操作结果为成功，携带数据
     * @param data
     * @return
     */
    public static <E> ResultEntity<E> successWithData(E data) {
        return new ResultEntity<E>(SUCCESS, NO_MESSAGE, data);
    }
    /**
     * 返回操作结果为失败，不带数据
     * @param message
     * @return
     */
    public static <E> ResultEntity<E> failed(String message) {
        return new ResultEntity<E>(FAILED, message, null);
    }
    private String operationResult;
    private String operationMessage;
    private T queryData;
    public ResultEntity() {
    }
    public ResultEntity(String operationResult, String operationMessage, T queryData) {
        super();
        this.operationResult = operationResult;
        this.operationMessage = operationMessage;
        this.queryData = queryData;
    }
    @Override
    public String toString() {
        return "AjaxResultEntity [operationResult=" + operationResult + ", operationMessage="
                + operationMessage
                + ", queryData=" + queryData + "]";
    }
    public String getOperationResult() {
        return operationResult;
    }
    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }
    public String getOperationMessage() {
        return operationMessage;
    }
    public void setOperationMessage(String operationMessage) {
        this.operationMessage = operationMessage;
    }
    public T getQueryData() {
        return queryData;
    }
    public void setQueryData(T queryData) {
        this.queryData = queryData;
    }

}
