package com.xtn.common.config;

import com.xtn.common.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

/**
 * MVC拦截器
 *
 * @author 86138
 */
@Component
public class AppWebMvcConfig implements WebMvcConfigurer {

    @Resource
    private JwtUtils jwtUtils;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 允许静态资源访问
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 后管拦截器
//        registry.addInterceptor(new BackApplicationInterceptor())
//                .addPathPatterns("/back/**");

        // 用户端拦截器
        registry.addInterceptor(new FrontApplicationInterceptor(jwtUtils))
                .addPathPatterns("/front/**");
    }

    /**
     * 重写父类提供的跨域请求处理的接口
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加映射路径
        registry.addMapping("/**")
                // 放行哪些原始域
                .allowedOriginPatterns("*")
                // 是否发送Cookie信息
                .allowCredentials(true)
                // 放行哪些原始域(请求方式)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // 放行哪些原始域(头部信息)
                .allowedHeaders("*");
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 设置允许跨域请求的域名
        config.addAllowedOrigin("*");
        // 是否允许证书 不再默认开启
        config.setAllowCredentials(true);
        // 设置允许的方法
        config.addAllowedMethod("*");
        // 允许任何头
        config.addAllowedHeader("*");
        config.addExposedHeader("auth-token");

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //返回Cors过滤器Bean
        return new CorsFilter(configSource);
    }

}
