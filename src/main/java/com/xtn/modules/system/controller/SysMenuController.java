package com.xtn.modules.system.controller;

import com.xtn.modules.system.service.SysMenuService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 菜单表
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@RestController
@RequestMapping("/sysMenu")
@Api(tags="菜单表")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

}