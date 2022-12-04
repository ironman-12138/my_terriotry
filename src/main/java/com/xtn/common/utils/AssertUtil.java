package com.xtn.common.utils;

import com.xtn.common.exception.BusinessException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author omen
 * @date 2021/08/10 17:20
 */
public final class AssertUtil {

    private AssertUtil() {
        throw new RuntimeException("不能实例化的工具类");
    }

    public static void isTrue(boolean flag, String errorMsg) {
        isTrue(flag, "500", errorMsg);
    }

    public static void isTrue(boolean flag, String code, String errorMsg) {
        if (flag) {
            throw new BusinessException(code, errorMsg);
        }
    }

    public static void isFalse(boolean flag, String errorMsg) {
        isFalse(flag, "500", errorMsg);
    }

    public static void isFalse(boolean flag, String code, String errorMsg) {
        if (!flag) {
            throw new BusinessException(code, errorMsg);
        }
    }

    public static void isNull(Object obj, String errorMsg) {
        isNull(obj, "500", errorMsg);
    }

    public static void isNull(Object obj, String code, String errorMsg) {
        if (obj == null) {
            throw new BusinessException(code, errorMsg);
        }
    }

    public static void isNotNull(Object obj, String errorMsg) {
        isNotNull(obj, "500", errorMsg);
    }

    public static void isNotNull(Object obj, String code, String errorMsg) {
        if (obj != null) {
            throw new BusinessException(code, errorMsg);
        }
    }

    public static void isBlank(String str, String errorMsg) {
        isBlank(str, "500", errorMsg);
    }

    public static void isBlank(String str, String code, String errorMsg) {
        if (!StringUtils.hasText(str)) {
            throw new BusinessException(code, errorMsg);
        }
    }

    public static void isListEmpty(List<?> list, String errorMsg) {
        isListEmpty(list, "500", errorMsg);
    }

    public static void isListEmpty(List<?> list, String code, String errorMsg) {
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(code, errorMsg);
        }
    }

    public static void isMapEmpty(Map<?, ?> map, String errorMsg) {
        isMapEmpty(map, "500", errorMsg);
    }

    public static void isMapEmpty(Map<?, ?> map, String code, String errorMsg) {
        if (Objects.isNull(map)) {
            throw new BusinessException(code, errorMsg);
        }
    }

    /**
     * author: omen
     *
     * @param test      : 检查的T 编译对象
     * @param predicate : Predicate<T> 的实例  断言策略
     * @param errorMsg  : 错误消息
     */
    public static <T> void assertionPolicy(T test, Predicate<T> predicate, String errorMsg) {
        assertionPolicy(test, predicate, "500", errorMsg);
    }

    public static <T> void assertionPolicy(T t, Predicate<T> predicate, String code, String errorMsg) {
        if (predicate.test(t)) {
            throw new BusinessException(code, errorMsg);
        }
    }
}
