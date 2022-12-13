package com.xtn.modules.blog.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Data
public class Blog implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@ApiModelProperty("id")
	private Long id;

    /**
     * 用户ID
     */
	@ApiModelProperty("用户ID")
	private Long userId;

    /**
     * 标题
     */
	@ApiModelProperty("标题")
	private String title;

    /**
     * 内容
     */
	@ApiModelProperty("内容")
	private String content;

    /**
     * 创建时间
     */
	@ApiModelProperty("创建时间")
	private Date createTime;

    /**
     * 创建人id
     */
	@ApiModelProperty("创建人id")
	private Long createId;

    /**
     * 是否公开
     */
	@ApiModelProperty("是否公开")
	private Boolean open;

    /**
     * 博客标签id（逗号分隔）
     */
	@ApiModelProperty("博客标签id（逗号分隔）")
	private String labelId;

    /**
     * 删除标记
     */
	@ApiModelProperty("删除标记")
	private Long deleteAt;
}