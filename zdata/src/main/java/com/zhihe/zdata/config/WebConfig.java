package com.zhihe.zdata.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan({"com.zhihe.zdata"})
public class WebConfig extends WebMvcConfigurerAdapter {


    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {

        MappingJackson2HttpMessageConverter jackson2 = new MappingJackson2HttpMessageConverter();
        converters.add(jackson2);
        //log.info("add MappingJackson2HttpMessageConverter");

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        converters.add(stringHttpMessageConverter);
        //log.info("add StringHttpMessageConverter");
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
        configurer.favorPathExtension(false);
        Map<String, MediaType> media_types = new HashMap<String, MediaType>();
        media_types.put("json", MediaType.APPLICATION_JSON);
        configurer.mediaTypes(media_types);
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer() {
        ClassPathResource resource = new ClassPathResource("application.properties");
        PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocation(resource);
        return propertyPlaceholderConfigurer;
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(passportInterceptor).addPathPatterns("/**");
        //registry.addInterceptor(iamInterceptor).addPathPatterns("/**");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }


    //静态资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /*----------------------配置thymeleaf视图解析起-------------------------------------*/
    @Bean
    public SpringResourceTemplateResolver springResourceTemplateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/thymeleaf/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(springResourceTemplateResolver());
        return templateEngine;
    }
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine());
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        return thymeleafViewResolver;
    }
	/*----------------------配置thymeleaf视图解析止-------------------------------------*/
}
