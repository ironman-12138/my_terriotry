package com.xtn.modules.system.controller;

import com.xtn.modules.system.service.SysRoleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 角色表
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@RestController
@RequestMapping("/sysRole")
@Api(tags="角色表")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

}