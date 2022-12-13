package com.xtn.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtn.modules.system.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;


/**
 * 菜单表
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
	
}