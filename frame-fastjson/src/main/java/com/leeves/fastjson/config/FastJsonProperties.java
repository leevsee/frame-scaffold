package com.leeves.fastjson.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.leeves.fastjson.converter.MyFastJsonHttpMessageConverter;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Description: TODO
 * Package: com.leeves.fastjsondemo.config
 *
 * @author Leeves
 * @date 2018-05-05
 */

@ConfigurationProperties("spring.http.converters.fastjson")
public class FastJsonProperties {

    private List<SerializerFeature> features;
    private Class<? extends FastJsonHttpMessageConverter> converter;


    public FastJsonProperties() {
        converter = MyFastJsonHttpMessageConverter.class;
    }

    public List<SerializerFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<SerializerFeature> features) {
        this.features = features;
    }

    public Class<? extends FastJsonHttpMessageConverter> getConverter() {
        return converter;
    }

    public void setConverter(Class<? extends FastJsonHttpMessageConverter> converter) {
        this.converter = converter;
    }
}
