package com.xtn.modules.system.controller;

import com.xtn.common.annotation.NoLoginNeed;
import com.xtn.common.base.Result;
import com.xtn.modules.system.service.SysUserService;
import com.xtn.modules.system.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * 登录
 * @author xCoder
 */
@RestController
@RequestMapping("/")
@Api(tags="登录")
public class LoginController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 用户登录
     * @param userLoginVo 登录接口请求参数(用户手机号、验证码)
     * @return token、过期时间、用户名
     */
    @NoLoginNeed
    @PostMapping(value = "login")
    @ApiOperation("用户登录")
    public Result<Map<String, Object>> login(@Valid @RequestBody UserLoginVo userLoginVo){
        return Result.ok(sysUserService.login(userLoginVo));
    }

}
