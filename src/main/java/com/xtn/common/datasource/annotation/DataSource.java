
package com.xtn.common.datasource.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 *
 * @author xCoder
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}
