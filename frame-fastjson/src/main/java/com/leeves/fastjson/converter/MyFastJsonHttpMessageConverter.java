package com.leeves.fastjson.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.leeves.fastjson.bean.FastJsonFilterObject;
import com.leeves.fastjson.filter.SimpleSerializerFilter;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Description: fastjson转换器
 * Package: com.leeves.fastjsondemo.converter
 *
 * @author Leeves
 * @date 2018-05-02
 */

public class MyFastJsonHttpMessageConverter extends FastJsonHttpMessageConverter {

    private static Charset UTF_8 = Charset.forName("UTF-8");

    public MyFastJsonHttpMessageConverter() {
        setSupportedMediaTypes(
                Arrays.asList(
                        new MediaType("application", "json", UTF_8),
                        new MediaType("application", "*+json", UTF_8),
                        new MediaType("application", "jsonp", UTF_8),
                        new MediaType("application", "*+jsonp", UTF_8)
                )
        );
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream out = outputMessage.getBody();
        if (object instanceof FastJsonFilterObject) {
            FastJsonFilterObject fastJsonFilterObject = (FastJsonFilterObject) object;
            String text = toJSONString(fastJsonFilterObject);
            byte[] bytes = text.getBytes(getFastJsonConfig().getCharset());
            out.write(bytes);
        } else {
            String text = JSON.toJSONString(object, getFastJsonConfig().getSerializerFeatures());
            byte[] bytes = text.getBytes(getFastJsonConfig().getCharset());
            out.write(bytes);
        }

    }

    private String toJSONString(FastJsonFilterObject filterObject) {
        SimpleSerializerFilter filter = new SimpleSerializerFilter(filterObject.getIncludes(), filterObject.getExcludes());
        String text = JSON.toJSONString(filterObject.getData(), filter, getFastJsonConfig().getSerializerFeatures());
        String callback = filterObject.getCallback();
        if (!(null == callback || callback.isEmpty())) {
            text = callback + "(" + text + ")";
        }
        return text;
    }

}
