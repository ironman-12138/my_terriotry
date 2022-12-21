package com.xtn.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xCoder
 */
@Data
public class Paging {

    /**
     * 页号
     */
    @ApiModelProperty("页号")
    private Integer pageIndex = 1;

    /**
     * 每页条数
     */
    @ApiModelProperty("每页条数")
    private Integer pageSize = 10;

}
