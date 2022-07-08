package com.yijiang.mall.constant;

import java.util.Map;

/**
 * @ClassName MallConstant
 * @Description
 * @Author 姜泽昊
 * @Date 2022/3/20 16:40
 * @Version 1.0
 */
public class MallConstant {

    //使用常量来管理一些字符串，防止我们敲错，因为字符串编译器没有错误检查，而常量有错误检查
    public static final String MESSAGE_LOGIN_FAILED = "登录失败！请确认账号密码是否正确！";
    public static final String MESSAGE_LOGIN_ACCT_IN_USE = "该账号已登录！";
    public static final String MESSAGE_ACCESS_FORBIDEN = "请登录以后再访问！";
    public static final String MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE = "系统错误：账号不唯一！";
    public static final String MESSAGE_DELEDTE_FAILED = "不能删除当前登录用户!";
    public static final String MESSAGE_LOGIN_ACCT_ALREADY_IN_EXIST = "该用户已存在!";
    public static final String MESSAGE_STRING_INVALIDATE = "字符串为空！";
    public static final String MESSAGE_ACCESS_DENIED = "抱歉！您没有权限访问！";
    public static final String MESSAGE_CODE_INVALIDATE = "验证码生成失败！";
    public static final String MESSAGE_CODE_NOT_EXIST = "验证码不存在！";
    public static final String MESSAGE_CODE_ERROR = "验证码错误！";
    public static final String MESSAGE_HEADER_PIC_UPLOAD_FAILED = "头图上传失败！";
    public static final String MESSAGE_HEADER_PIC_EMPTY = "头图不能为空！";
    public static final String MESSAGE_DETAIL_PIC_EMPTY = "详情图片不能为空！";
    public static final String MESSAGE_DETAIL_PIC_UPLOAD_FAILED = "详情图片上传失败，请重试！";
    public static final String MESSAGE_LOGIN_ACCT_ALREADY_IN_USE = "该账号已登录";
    public static final String MESSAGE_RETURN_PIC_EMPTY = "上传回报图片不能为空！";
    public static final String MESSAGE_TEMPLE_PROJECT_MISSING = "临时ProjectVO对象未找到！";

    public static final String ATTR_NAME_EXCEPTION = "exception";
    public static final String ATTR_NAME_LOGIN_ADMIN = "loginAdmin";
    public static final String ATTR_NAME_LOGIN_MEMBER = "loginMember";
    public static final String ATTR_NAME_PAGE_INFO = "pageInfo";
    public static final String ATTR_NAME_ASSIGNED_ROLE = "assignedRoleList";
    public static final String ATTR_NAME_MESSAGE = "message";
    public static final String ATTR_NAME_UNASSIGNED_ROLE = "unAssignedRoleList";
    public static final String ATTR_NAME_TEMPLE_PROJECT = "tempProject";

    public static final String REDIS_CODE_PREFIX = "REDIS_CODE_PREFIX_";

    public static final String ATTR_NAME_PORTAL_TYPE_LIST = "portal_type_list";
    public static final String ATTR_NAME_DETAIL_PROJECT = "detailProjectVO";

}
