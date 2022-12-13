package com.xtn.modules.system.service.impl;

import com.xtn.modules.system.mapper.SysMenuMapper;
import com.xtn.modules.system.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 菜单表接口实现类
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;


}