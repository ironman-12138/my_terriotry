package com.xtn.common.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 * @author luoyang
 * @date 2021/12/6 10:47
 */
@Data
public class LogEo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String username;

    private String ip;

    private String browser;

    private String requestParam;

    private String uri;

    /**
     * 请求状态
     */
    private Integer status;

    private String content;

    /**
     * 请求异常时内容
     */
    private String error;

    /**
     * 请求耗时
     */
    private Long requestTime;

    private Date createTime;
}
