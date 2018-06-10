package com.leeves.browser.web.conf;

import com.leeves.browser.web.filter.WebFilter;
import com.leeves.browser.web.interceptor.WebInterceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;


/**
 * Description:
 * 配置Filer（如果自定义Fitler没有加@Component）:
 * 相当于XML形式配置Filter，且可以配置路径。也可以配置第三方Filter
 *
 * 配置Interceptor（自定义interceptor可加可不加@Component）:
 * 在spring boot 2.0中需要实现WebMvcConfigurer，在1.5版本中要继承WebMvcConfigurerAdapter
 *
 * Package: com.leeves.browser.conf
 *
 * @author Leeves
 * @version 1.0.0  2018-06-10
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private WebInterceptor mWebInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebInterceptor webInterceptor = new WebInterceptor();
        registry.addInterceptor(webInterceptor);
    }

    @Bean
    public FilterRegistrationBean webFiter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        WebFilter webFilter =  new WebFilter();
        filterRegistrationBean.setFilter(webFilter);
        List<String> urlList = new ArrayList<>();
        //配置路径
        urlList.add("/*");
        filterRegistrationBean.setUrlPatterns(urlList);
        return filterRegistrationBean;
    }

}
