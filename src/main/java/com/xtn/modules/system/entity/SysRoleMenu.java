package com.xtn.modules.system.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色菜单关联表
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Data
public class SysRoleMenu implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
	@ApiModelProperty("角色ID")
	private Long roleId;

    /**
     * 菜单/按钮ID
     */
	@ApiModelProperty("菜单/按钮ID")
	private Long menuId;
}