package com.xtn.modules.system.service.impl;

import com.xtn.modules.system.mapper.SysUserRoleMapper;
import com.xtn.modules.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户角色关联表接口实现类
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;


}