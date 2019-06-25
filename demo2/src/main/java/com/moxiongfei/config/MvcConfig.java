package com.moxiongfei.config;

import com.moxiongfei.converter.DateConverter;
import com.moxiongfei.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;

@ComponentScan(basePackages = "com.moxiongfei.controller")                     //替代springmvc的注解开关
@EnableWebMvc                                                                  //替代springmvc的驱动开关
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /*替代视图解析器*/
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }
    /*替代处理器（即数据转换器）*/
    @Autowired
    ConfigurableConversionService conversionService;
    @PostConstruct
    public void addConverter(){
        conversionService.addConverter(new DateConverter());
    }
    @Bean
    @Primary
    public ConfigurableConversionService configurableConversionService(){
        return conversionService;
    }
    /*替代拦截器*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/user/auth/**");
    }
    /*替代resources静态资源处理器*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //前面的是mapping，后面的是location
        registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/img/");              //访问web应用的目录
        registry.addResourceHandler("/img2/**").addResourceLocations("classpath:/img/");           //访问classpath（一般是resources目录）的目录
        registry.addResourceHandler("/img3/**").addResourceLocations("file:F://wallpaper/图标/哔哩哔哩/");       //访问本地的目录
    }
    /*替代文件上传*/
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(512000);                     //限制图片大小为500k
        return commonsMultipartResolver;
    }
}
