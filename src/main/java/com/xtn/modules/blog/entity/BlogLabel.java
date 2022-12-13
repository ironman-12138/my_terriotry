package com.xtn.modules.blog.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 博客标签
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Data
public class BlogLabel implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@ApiModelProperty("id")
	private Long id;

    /**
     * 标签名称
     */
	@ApiModelProperty("标签名称")
	private String labelName;

    /**
     * 删除标记
     */
	@ApiModelProperty("删除标记")
	private Long deleteAt;
}