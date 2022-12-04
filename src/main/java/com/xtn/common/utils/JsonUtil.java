package com.xtn.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json工具类
 *
 * @author jiangjh
 * @date 2020/4/1 18:32
 */

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 把对象转化为jsonString
     *
     * @param o
     * @return
     */
    public static String toJsonString(Object o) {
        if (o != null) {
            try {
                return mapper.writeValueAsString(o);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            return "";
        }
    }

    /**
     * 解析json数组
     *
     * @param jsonArray
     * @return
     */
    public static List<Map> parseArray(String jsonArray) {
        if (StringUtils.hasText(jsonArray)) {
            try {
                return mapper.readValue(jsonArray, List.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 解析字符串
     *
     * @param json
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T parse(String json, Class<T> c) {
        if (StringUtils.hasText(json)) {
            try {
                return mapper.readValue(json, c);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }
}
