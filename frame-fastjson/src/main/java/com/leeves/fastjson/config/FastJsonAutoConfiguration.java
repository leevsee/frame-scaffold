package com.leeves.fastjson.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.leeves.fastjson.converter.MyFastJsonHttpMessageConverter;
import com.leeves.utils.CollectionUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Description: FastJson自动配置
 * Package: com.leeves.fastjsondemo.config
 *
 * @author Leeves
 * @date 2018-05-05
 */
@Configuration
@ConditionalOnClass(name = "com.alibaba.fastjson.JSON")
public class FastJsonAutoConfiguration {

    @Configuration
    @ConditionalOnClass(name = {"com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter"})
    @ConditionalOnProperty(name = {"spring.http.converters.preferred-json-mapper"}, havingValue = "fastjson", matchIfMissing = true)
    @ConditionalOnWebApplication
    @EnableConfigurationProperties(FastJsonProperties.class)
    protected static class FastJson2HttpMessageConverterConfiguration {

        @Autowired
        private FastJsonProperties properties;

        @Bean
        @ConditionalOnMissingBean(FastJsonHttpMessageConverter.class)
        public HttpMessageConverters customConverters(MyFastJsonHttpMessageConverter converter) {
            Collection<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

            if (null == converter) {
                Class<?> converterClass = properties.getConverter();
                converter = (MyFastJsonHttpMessageConverter) BeanUtils.instantiate(converterClass);
            }

            FastJsonConfig config = new FastJsonConfig();
            List<SerializerFeature> features = properties.getFeatures();
            if (!CollectionUtils.isBlank(features)) {
                SerializerFeature[] featureArray = new SerializerFeature[features.size()];
                config.setSerializerFeatures(features.toArray(featureArray));
            }

            converter.setFastJsonConfig(config);
            messageConverters.add(converter);

            return new HttpMessageConverters(true, messageConverters);
        }

        @Bean
        @ConditionalOnMissingBean(MyFastJsonHttpMessageConverter.class)
        public MyFastJsonHttpMessageConverter converter() {
            return new MyFastJsonHttpMessageConverter();
        }
    }

}
