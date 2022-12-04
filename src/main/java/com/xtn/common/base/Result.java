package com.xtn.common.base;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static java.lang.System.currentTimeMillis;

/**
 * 响应对象
 *
 * @author xCoder
 * @param <T>
 */
@Data
@ApiModel("返回类")
@NoArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "code码")
    private String code;

    @ApiModelProperty(value = "提示信息")
    private String msg;

    @ApiModelProperty(value = "返回对象")
    private T data;

    @ApiModelProperty(value = "当前时间戳")
    private long timestamp = currentTimeMillis();

    /**
     * 默认成功返回
     */
    public static <T> Result<T> ok() {
        return ok(null);
    }

    /**
     * 含有返回数据的成功返回
     */
    public static <T> Result<T> ok(T t) {
        Result<T> result = new Result<T>();
        result.setCode(ResultCode.SUCCESS_CODE);
        result.setMsg(ResultCode.SUCCESS_DESC);
        if (t != null) {
            result.setData(t);
        }
        return result;
    }

    /**
     * 含有错误码及自定义错误信息的错误返回
     */
    public static <T> Result<T> error(String respCode, String respDesc) {
        return new Result<T>() {{
            setCode(respCode);
            setData(null);
            setMsg(respDesc);
        }};
    }

}
