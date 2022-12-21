package com.xtn.modules.blog.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xCoder
 */
@Data
public class BlogSaveRequest {

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
     * 是否公开
     */
    @ApiModelProperty("是否公开")
    private Boolean open;

    /**
     * 博客标签id（逗号分隔）
     */
    @ApiModelProperty("博客标签id（逗号分隔）")
    private String labelId;

}
