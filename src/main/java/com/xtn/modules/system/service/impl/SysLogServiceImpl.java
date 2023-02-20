package com.xtn.modules.system.service.impl;

import com.xtn.modules.system.entity.SysLog;
import com.xtn.modules.system.mapper.SysLogMapper;
import com.xtn.modules.system.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 日志表接口实现类
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public void saveLog(String methodName, String status, String message, String username, String requestParam, String serialNumber, Long time) {
        SysLog sysLog = new SysLog();
        sysLog.setMethodName(methodName);
        sysLog.setStatus(status);
        sysLog.setMessage(message);
        sysLog.setUsername(username);
        sysLog.setRequestParam(requestParam);
        sysLog.setSerialNumber(serialNumber);
        sysLog.setTime(time);
        sysLogMapper.insert(sysLog);
    }
}
