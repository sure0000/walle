package com.dashu.log.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/12/21 下午3:34
 **/
@Configuration
public class ApplicationConf extends WebMvcConfigurerAdapter {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(36000)
                .allowCredentials(true);
    }
}
