package com.xtn.common.base;

/**
 * 接口相关常量
 * @author xCoder
 * @Date 2019-09-23 11:00
 */
public interface ResultCode {

    /**
     * 接口调用成功
     */
    String SUCCESS_CODE = "200";
    String SUCCESS_DESC = "成功";

    /**
     * 系统异常,未知错误
     */
    String UN_KNOW_ERROR = "401";
    String UN_KNOW_ERROR_DESC = "系统异常";

    /**
     * 参数异常
     */
    String PARAM_ERROR = "402";
    String PARAM_ERROR_DESC = "参数异常";

    /**
     * 未登录或已过期
     */
    String LOGIN_ERROR = "403";
    String LOGIN_ERROR_DESC = "未登录或登录已过期";

    /**
     * 业务异常,需要提示信息
     */
    String BUSINESS_ERROR = "404";
    String BUSINESS_ERROR_DESC = "业务异常";


}
