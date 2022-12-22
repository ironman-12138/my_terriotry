package com.xtn.modules.blog.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xCoder
 */
@Data
public class BlogRequest {

    public interface Update {}

    public interface Add {}

    /**
     * 博客id
     */
    @NotNull(message = "id不能为空", groups = Update.class)
    @ApiModelProperty("博客id")
    private Long id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = {Add.class, Update.class})
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
