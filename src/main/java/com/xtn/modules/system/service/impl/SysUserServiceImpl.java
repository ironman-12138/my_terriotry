package com.xtn.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtn.common.utils.JwtUtils;
import com.xtn.common.utils.PasswordUtil;
import com.xtn.modules.system.entity.SysUser;
import com.xtn.modules.system.mapper.SysUserMapper;
import com.xtn.modules.system.service.SysUserService;
import com.xtn.modules.system.vo.UserLoginVo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sun.security.provider.MD5;

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

        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("username", userLoginVo.getUsername()));
        Assert.isTrue(Objects.nonNull(sysUser), "用户名或密码错误");
        Assert.isTrue(PasswordUtil.checkPassword(userLoginVo.getPassword(), sysUser.getSalt(), sysUser.getPassword()), "用户名或密码错误");
        //生成token
        String token = jwtUtils.generateToken(sysUser.getId(), sysUser.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());
        map.put("username", sysUser.getUsername());
        return map;
    }
}