package com.xtn.modules.system.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xCoder
 */
@Data
public class UserLoginVo {

    /**
     * 1：后台用户，2：微信小程序用户
     */
    @ApiModelProperty("1：后台用户，2：微信小程序用户")
    private Integer type;

}
