package com.xtn.modules.system.service.impl;

import com.xtn.modules.system.mapper.SysRoleMenuMapper;
import com.xtn.modules.system.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色菜单关联表接口实现类
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;


}