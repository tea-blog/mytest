package com.anpai.servicebase;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xiesongzhi
 * @Description:
 * @create: 2020/6/7 1:58
 */
@Configuration   // 配置类
@EnableSwagger2  // swagger注解
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("鞋子批发店管理接口测试")
                .description("本文档描述了鞋子批发店管理接口定义")
                .version("1.0")
                .contact(new Contact("南方者", "https://www.jianshu.com/u/b6e7ada3263a", "ai.77798@qq.com"))
                .build();
    }
}
