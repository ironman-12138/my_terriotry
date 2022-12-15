package com.xtn.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtn.common.utils.JwtUtils;
import com.xtn.modules.system.entity.SysUser;
import com.xtn.modules.system.mapper.SysUserMapper;
import com.xtn.modules.system.service.SysUserService;
import com.xtn.modules.system.vo.UserLoginVo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户表接口实现类
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public Map<String, Object> login(UserLoginVo userLoginVo) {

        SysUser user = new SysUser();

        //生成token
        String token = jwtUtils.generateToken(user.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());
        map.put("username", user.getUsername());
        return map;
    }
}