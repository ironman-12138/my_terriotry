package com.xtn.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

@Slf4j
@Component
public class SpringContextUtil implements ApplicationContextAware {

    public static final String env_default = "default";
    public static final String env_test = "test";
    public static final String env_prod = "prod";

    /**
     * IOC 容器
     */
    private static ApplicationContext applicationContext;
    private static String configEvn;

    @Value("${spring.profiles.active}")
    public void setConfigEvn(String cEnv) {
        SpringContextUtil.configEvn = cEnv;
    }

    /**
     * 是否是测试环境
     *
     * @return
     */
    public static boolean isTest() {
        return env_test.equalsIgnoreCase(configEvn);
    }

    /**
     * 是否是开发环境
     *
     * @return
     */
    public static boolean isDefault() {
        return env_default.equalsIgnoreCase(configEvn);
    }

    /**
     * 是否是生产环境
     *
     * @return
     */
    public static boolean isProd() {
        return env_prod.equalsIgnoreCase(configEvn);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
        String activeProfile = applicationContext.getEnvironment().getActiveProfiles()[0];
        log.info("当前使用的环境[{}]", activeProfile);
    }

    /**
     * @return 返回IOC容器
     */
    public ApplicationContext getApplictionContext() {
        return applicationContext;
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
     * @param beanName bean名称
     * @Description: 获取spring容器中的bean, 通过bean名称获取
     * @return: Object 返回Object,需要做强制类型转换
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * @param beanClass bean 类型
     * @Description: 获取spring容器中的bean, 通过bean类型获取
     * @return: T 返回指定类型的bean实例
     */
    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    /**
     * 根据class 获取所有的bean
     *
     * @param <T>
     * @param beanClass
     * @return
     */
    public static <T> Map<String, T> getBeans(Class<T> beanClass) {
        return applicationContext.getBeansOfType(beanClass);
    }

    /**
     * @param beanName  bean 名称
     * @param beanClass bean 类型
     * @Description: 获取spring容器中的bean, 通过bean名称和bean类型精确获取
     * @return: T 返回指定类型的bean实例
     */
    public static <T> T getBean(String beanName, Class<T> beanClass) {
        return applicationContext.getBean(beanName, beanClass);
    }

    /**
     * 找出所有具有指定注解（或注解的子类）的beans
     * @param clazz
     * @return
     */
    public static Collection<Object> getBeanByAnnotation(Class <? extends Annotation> clazz) {
        return applicationContext.getBeansWithAnnotation(clazz).values();
    }
}