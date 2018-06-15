package com.leeves.fastjson.filter;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Description: fastjson过滤类
 * Package: com.leeves.fastjson.filter
 *
 * @author Leeves
 * @version 1.0.0  2018-06-15
 */
public class SimpleSerializerFilter extends SimplePropertyPreFilter {

    private Map<Class,HashSet<String>> includes;
    private Map<Class,HashSet<String>> excludes;

    public SimpleSerializerFilter(Map<Class, HashSet<String>> includes, Map<Class, HashSet<String>> excludes) {
        this.includes = includes;
        this.excludes = excludes;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean apply(JSONSerializer serializer, Object source, String name) {

        if (isNotBlank(includes)) {
            for (Map.Entry<Class, HashSet<String>> include : includes.entrySet()) {
                Class objClass = include.getKey();
                Set<String> includeProp = include.getValue();
                if (objClass.isAssignableFrom(source.getClass())) {
                    return includeProp.contains(name);
                }
            }
        }

        if (isNotBlank(excludes)) {
            for (Map.Entry<Class, HashSet<String>> exclude : excludes.entrySet()) {
                Class objClass = exclude.getKey();
                Set<String> includeProp = exclude.getValue();
                if (objClass.isAssignableFrom(source.getClass())) {
                    return !includeProp.contains(name);
                }
            }
        }

        return true;
    }

    private <K, V> boolean isNotBlank(Map<K, V> data) {
        return !(null == data || data.isEmpty());
    }
}
