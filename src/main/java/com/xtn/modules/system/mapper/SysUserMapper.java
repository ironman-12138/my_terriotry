package com.xtn.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtn.modules.system.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户表
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
	
}