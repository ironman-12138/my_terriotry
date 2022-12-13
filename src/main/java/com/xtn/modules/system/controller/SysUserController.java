package com.xtn.modules.system.controller;

import com.xtn.modules.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 用户表
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@RestController
@RequestMapping("/sysUser")
@Api(tags="用户表")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

}