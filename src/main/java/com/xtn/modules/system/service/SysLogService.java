package com.xtn.modules.system.service;

/**
 * 菜单表接口
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
public interface SysLogService {

    /**
     * 保存日志
     * @param methodName 方法名称
     * @param status 返回状态值
     * @param message 返回状态信息
     * @param username 调用者
     * @param requestParam 请求参数
     * @param serialNumber 流水号
     * @param time 耗时
     */
    void saveLog(String methodName, String status, String message, String username, String requestParam, String serialNumber, Long time);
}