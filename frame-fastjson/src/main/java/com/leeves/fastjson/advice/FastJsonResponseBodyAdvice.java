package com.leeves.fastjson.advice;


import com.leeves.fastjson.annotation.JSONP;
import com.leeves.fastjson.annotation.MoreSerializeField;
import com.leeves.fastjson.annotation.SerializeField;
import com.leeves.fastjson.bean.FastJsonFilterObject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Description: TODO
 * Package: com.leeves.fastjsondemo.advice
 *
 * @author Leeves
 * @date 2018-05-01
 */
@Order(1)
@ControllerAdvice
@ConditionalOnWebApplication
public class FastJsonResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (o == null) {
            return null;
        }

        FastJsonFilterObject jsonFilterObject = new FastJsonFilterObject();

        Method parameterMethod = methodParameter.getMethod();
        boolean jsonpAnnotation = parameterMethod.isAnnotationPresent(JSONP.class);
        boolean serializeFieldAnnotation = parameterMethod.isAnnotationPresent(SerializeField.class);
        boolean MoreSerializeFieldAnnotation = parameterMethod.isAnnotationPresent(MoreSerializeField.class);

        if (!jsonpAnnotation && !serializeFieldAnnotation && !MoreSerializeFieldAnnotation) {
            return o;
        }

        if (jsonpAnnotation) {
            Object obj = parameterMethod.getAnnotation(JSONP.class);
            handleAnnotation(JSONP.class, obj, jsonFilterObject);
        }

        if (serializeFieldAnnotation) {
            Object obj = parameterMethod.getAnnotation(SerializeField.class);
            handleAnnotation(SerializeField.class, obj, jsonFilterObject);
        }

        if (MoreSerializeFieldAnnotation) {
            SerializeField[] serializeFields = parameterMethod.getAnnotation(MoreSerializeField.class).value();
            if (serializeFields.length > 0) {
                for (SerializeField serializeField : serializeFields) {
                    handleAnnotation(SerializeField.class, serializeField, jsonFilterObject);
                }
            }
        }

        jsonFilterObject.setData(o);

        return jsonFilterObject;
    }


    private void handleAnnotation(Class clazz, Object obj, FastJsonFilterObject jsonFilterObject) {
        String[] includes = {};
        String[] excludes = {};
        Class objClass = null;
        if (clazz.equals(JSONP.class)) {
            JSONP jsonp = (JSONP) obj;
            jsonFilterObject.setCallback(jsonp.value());
        }
        if (clazz.equals(SerializeField.class)) {
            SerializeField serializeField = (SerializeField) obj;
            includes = serializeField.includes();
            excludes = serializeField.excludes();
            objClass = serializeField.clazz();
        }

        if (includes.length > 0) {
            jsonFilterObject.getIncludes().put(objClass, new HashSet<>(Arrays.asList(includes)));
        }
        if (excludes.length > 0) {
            jsonFilterObject.getExcludes().put(objClass, new HashSet<>(Arrays.asList(excludes)));
        }
    }

}
