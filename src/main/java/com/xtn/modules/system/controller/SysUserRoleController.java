package com.xtn.modules.system.controller;

import com.xtn.modules.system.service.SysUserRoleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 用户角色关联表
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@RestController
@RequestMapping("/sysUserRole")
@Api(tags="用户角色关联表")
public class SysUserRoleController {

    @Resource
    private SysUserRoleService sysUserRoleService;

}