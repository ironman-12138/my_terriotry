package com.xtn.modules.blog.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xCoder
 */
@Data
public class BlogLabelRequest {

    public interface Update {}

    public interface Add {}

    /**
     * 标签id
     */
    @NotNull(message = "id不能为空", groups = Update.class)
    @ApiModelProperty("标签id")
    private Long id;

    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空", groups = {Update.class, Add.class})
    @ApiModelProperty("标签名称")
    private String labelName;

}
