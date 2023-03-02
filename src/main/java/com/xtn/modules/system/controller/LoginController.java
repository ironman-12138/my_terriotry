package com.xtn.modules.system.controller;

import com.xtn.common.annotation.LoginUser;
import com.xtn.common.annotation.NoLoginNeed;
import com.xtn.common.base.Result;
import com.xtn.modules.system.entity.SysUser;
import com.xtn.modules.system.service.SysUserService;
import com.xtn.modules.system.service.WxService;
import com.xtn.modules.system.vo.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * 登录
 * @author xCoder
 */
@RestController
@RequestMapping("/")
@Api(tags="登录")
@AllArgsConstructor
public class LoginController {

    private final SysUserService sysUserService;
    private final WxService wxService;

    /**
     * 用户登录
     * @param userLoginVo 登录接口请求参数
     * @return token、过期时间、用户名
     */
    @NoLoginNeed
    @PostMapping(value = "login")
    @ApiOperation("用户登录")
    public Result<Map<String, Object>> login(@Valid @RequestBody UserLoginVo userLoginVo) {
        return Result.ok(sysUserService.login(userLoginVo));
    }

    /**
     * 当前登录用户详情
     * @param sysUser 当前登录用户
     * @return 当前登录用户详情
     */
    @PostMapping(value = "userDetail")
    @ApiOperation("当前登录用户详情")
    public Result<SysUser> userDetail(@LoginUser SysUser sysUser) {
        return Result.ok(sysUser);
    }

    /**
     * 获取微信凭证
     * @return 微信AccessToken
     */
    @ApiOperation("获取微信凭证")
    @PostMapping("getAccessToken")
    public String getAccessToken() {
        return wxService.getAccessToken();
    }

    /**
     * 微信小程序登录
     * @param code 微信code
     * @return 用户openId
     */
    @ApiOperation("微信小程序登录")
    @GetMapping("wxLogin")
    public String wxLogin(@RequestParam(required = true) String code) {
        return wxService.wxLogin(code);
    }

    /**
     * 微信小程序获取手机号
     * @param code 微信code
     * @return 手机号
     */
    @ApiOperation("微信小程序获取手机号")
    @GetMapping("getPhoneNumber")
    public String getPhoneNumber(@RequestParam(required = true) String code) {
        return wxService.getPhoneNumber(code);
    }

}
