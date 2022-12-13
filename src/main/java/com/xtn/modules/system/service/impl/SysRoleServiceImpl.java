package com.xtn.modules.system.service.impl;

import com.xtn.modules.system.mapper.SysRoleMapper;
import com.xtn.modules.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色表接口实现类
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;


}