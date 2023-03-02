package com.xtn.modules.system.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xCoder
 */
@Data
public class UserLoginVo {

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    @ApiModelProperty("账号")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;

}
