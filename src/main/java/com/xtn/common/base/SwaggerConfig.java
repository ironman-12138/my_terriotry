
package com.xtn.common.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket client() {
        return getDocket("用户端接口", "com.xtn.modules.blog.controller");
    }

    private Docket getDocket(String groupName, String classPackage) {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                //分组名称
                .groupName(groupName)
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage(classPackage))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("我的地盘")
            .description("我的地盘接口文档")
            .termsOfServiceUrl("https://www.baidu.com")
            .version("1.0.0")
            .build();
    }

    private List<ApiKey> security() {
        ApiKey apiKey = new ApiKey("token", "token", "header");
        List<ApiKey> list = new ArrayList<>();
        list.add(apiKey);
        return list;
    }

}