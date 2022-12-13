package com.xtn.modules.system.controller;

import com.xtn.modules.system.service.SysRoleMenuService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 角色菜单关联表
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@RestController
@RequestMapping("/sysRoleMenu")
@Api(tags="角色菜单关联表")
public class SysRoleMenuController {

    @Resource
    private SysRoleMenuService sysRoleMenuService;

}