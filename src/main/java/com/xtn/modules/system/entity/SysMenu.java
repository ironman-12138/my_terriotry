package com.xtn.modules.system.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Data
public class SysMenu implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 菜单/按钮ID
     */
	@ApiModelProperty("菜单/按钮ID")
	private Long id;

    /**
     * 上级菜单ID
     */
	@ApiModelProperty("上级菜单ID")
	private Long parentId;

    /**
     * 菜单/按钮名称
     */
	@ApiModelProperty("菜单/按钮名称")
	private String menuName;

    /**
     * 菜单URL
     */
	@ApiModelProperty("菜单URL")
	private String url;

    /**
     * 权限标识
     */
	@ApiModelProperty("权限标识")
	private String perms;

    /**
     * 图标
     */
	@ApiModelProperty("图标")
	private String icon;

    /**
     * 类型 0菜单 1按钮
     */
	@ApiModelProperty("类型 0菜单 1按钮")
	private Integer type;

    /**
     * 排序
     */
	@ApiModelProperty("排序")
	private Long orderNum;

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
     * 0：不可用，1：可用
     */
	@ApiModelProperty("0：不可用，1：可用")
	private Integer available;

    /**
     * 0:不展开，1：展开
     */
	@ApiModelProperty("0:不展开，1：展开")
	private Integer open;
}