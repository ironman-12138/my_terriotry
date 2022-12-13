package com.xtn.modules.system.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关联表
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Data
public class SysUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	@ApiModelProperty("用户ID")
	private Long userId;

    /**
     * 角色ID
     */
	@ApiModelProperty("角色ID")
	private Long roleId;
}