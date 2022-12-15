package com.xtn.modules.system.vo.wx;

import lombok.Data;

/**
 * @author xCoder
 */
@Data
public class Quota {

    /**
     * 当天该账号可调用该接口的次数
     */
    private Integer daily_limit;

    /**
     * 当天已经调用的次数
     */
    private Integer used;

    /**
     * 当天剩余调用次数
     */
    private Integer remain;

}
