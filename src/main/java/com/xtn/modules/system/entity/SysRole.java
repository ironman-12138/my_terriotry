package com.xtn.modules.system.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Data
public class SysRole implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
	@ApiModelProperty("角色ID")
	private Long id;

    /**
     * 角色名称
     */
	@ApiModelProperty("角色名称")
	private String roleName;

    /**
     * 角色描述
     */
	@ApiModelProperty("角色描述")
	private String remark;

    /**
     * 创建时间
     */
	@ApiModelProperty("创建时间")
	private Date createTime;

    /**
     * 修改时间
     */
	@ApiModelProperty("修改时间")
	private Date modifiedTime;

    /**
     * 是否可用,0:不可用，1：可用
     */
	@ApiModelProperty("是否可用,0:不可用，1：可用")
	private Integer status;
}