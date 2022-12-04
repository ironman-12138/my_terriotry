package com.xtn.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author 86138
 */
@Component
public class EnvironmentUtil {

    /**
     * 用来保存当前项目使用的配置文件
     */
    private static String configEvn;

    public static final String ENV_DEFAULT = "dev";
    public static final String ENV_TEST = "test";
    public static final String ENV_PROD = "prod";

    /**
     * 为静态域赋值
     * @param cEnv
     */
    @Value("${spring.profiles.active}")
    public void setConfigEvn(String cEnv) {
        configEvn = cEnv;
    }

    /**
     * 获取 配置文件中的数据
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        return SpringContextUtil.getBean(Environment.class).getProperty(key);
    }


    /**
     * 是否是测试环境
     *
     * @return
     */
    public static boolean isTest() {
        return ENV_TEST.equalsIgnoreCase(configEvn);
    }

    /**
     * 是否是开发环境
     *
     * @return
     */
    public static boolean isDefault() {
        return ENV_DEFAULT.equalsIgnoreCase(configEvn);
    }

    /**
     * 是否是生产环境
     *
     * @return
     */
    public static boolean isProd() {
        return ENV_PROD.equalsIgnoreCase(configEvn);
    }


    public static String currentEnv() {
        return EnvironmentUtil.getValue("spring.profiles.active");
    }
}
