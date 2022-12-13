package com.xtn.modules.system.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Data
public class SysUser implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	@ApiModelProperty("用户ID")
	private Long id;

    /**
     * 用户名
     */
	@ApiModelProperty("用户名")
	private String username;

    /**
     * 昵称
     */
	@ApiModelProperty("昵称")
	private String nickname;

    /**
     * 邮箱
     */
	@ApiModelProperty("邮箱")
	private String email;

    /**
     * 头像
     */
	@ApiModelProperty("头像")
	private String avatar;

    /**
     * 联系电话
     */
	@ApiModelProperty("联系电话")
	private String phoneNumber;

    /**
     * 状态 0锁定 1有效
     */
	@ApiModelProperty("状态 0锁定 1有效")
	private Integer enable;

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
     * 盐
     */
	@ApiModelProperty("盐")
	private String salt;

    /**
     * 0:超级管理员，1：系统用户
     */
	@ApiModelProperty("0:超级管理员，1：系统用户")
	private Integer type;

    /**
     * 密码
     */
	@ApiModelProperty("密码")
	private String password;
}