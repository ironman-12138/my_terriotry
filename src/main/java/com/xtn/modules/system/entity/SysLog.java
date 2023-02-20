package com.xtn.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统日志
 * @author xCoder
 * 2023-02-20
 */
@Data
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 方法名称
     */
    @ApiModelProperty("方法名称")
    private String methodName;

    /**
     * 返回状态值
     */
    @ApiModelProperty("返回状态值")
    private String status;

    /**
     * 返回状态信息
     */
    @ApiModelProperty("返回状态信息")
    private String message;

    /**
     * 调用者
     */
    @ApiModelProperty("调用者")
    private String username;

    /**
     * 请求参数
     */
    @ApiModelProperty("请求参数")
    private String requestParam;

    /**
     * 流水号
     */
    @ApiModelProperty("流水号")
    private String serialNumber;

    /**
     * 耗时
     */
    @ApiModelProperty("耗时")
    private Long time;

}
