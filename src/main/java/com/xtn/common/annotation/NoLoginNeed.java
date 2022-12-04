package com.xtn.common.annotation;

import java.lang.annotation.*;

/**
 * 用来验证是否需要验证登录的接口
 * @author xCoder
 */
@Documented
@Inherited
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoLoginNeed {
    boolean value() default true;
}