package com.leeves.fastjson.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Description: 序列定制化
 * Package: com.leeves.fastjsondemo.bean
 *
 * @author Leeves
 * @date 2018-05-01
 */

public class FastJsonFilterObject {

    private Object data;
    private Map<Class, HashSet<String>> includes;
    private Map<Class, HashSet<String>> excludes;
    private String callback;

    public FastJsonFilterObject() {
        this(null,new HashMap<>(), new HashMap<>());
    }

    public FastJsonFilterObject(Object data, Map<Class, HashSet<String>> includes, Map<Class, HashSet<String>> excludes) {
        this.data = data;
        this.includes = includes;
        this.excludes = excludes;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<Class, HashSet<String>> getIncludes() {
        return includes;
    }

    public void setIncludes(Map<Class, HashSet<String>> includes) {
        this.includes = includes;
    }

    public Map<Class, HashSet<String>> getExcludes() {
        return excludes;
    }

    public void setExcludes(Map<Class, HashSet<String>> excludes) {
        this.excludes = excludes;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
