package com.xtn.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xtn.modules.system.entity.SysUser;
import com.xtn.modules.system.vo.UserLoginVo;

import java.util.Map;

/**
 * 用户表接口
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
public interface SysUserService extends IService<SysUser> {

    Map<String, Object> login(UserLoginVo userLoginVo);
}