package com.xtn.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtn.modules.system.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;


/**
 * 角色菜单关联表
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
	
}