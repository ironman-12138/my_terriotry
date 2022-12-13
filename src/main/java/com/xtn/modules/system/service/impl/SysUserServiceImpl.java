package com.xtn.modules.system.service.impl;

import com.xtn.modules.system.mapper.SysUserMapper;
import com.xtn.modules.system.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户表接口实现类
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;


}