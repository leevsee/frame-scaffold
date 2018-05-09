package com.leeves.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 返回信息类
 * Package: com.leeves.base
 *
 * @author Leeves
 * @date 2018-05-09
 */

public class R<T> extends HashMap<String, Object> {
    public R() {
        put(BaseConst.RETRUN_CODE, BaseConst.RETRUN_SUCCESS_CODE);
        put(BaseConst.RETRUN_MSG, "处理成功");
    }

    public static R sysError() {
        return sysError(BaseConst.RETRUN_FAIL_CODE, "处理失败");
    }

    public static R sysError(String msg) {
        return sysError(BaseConst.RETRUN_FAIL_CODE, msg);
    }

    public static R sysError(String code, String msg) {
        R r = new R();
        r.put(BaseConst.RETRUN_CODE, code);
        r.put(BaseConst.RETRUN_MSG, msg);
        return r;
    }

    public static R resultError() {
        return resultError(BaseConst.RETRUN_FAIL_CODE, "处理失败");
    }

    public static R resultError(String msg) {
        return resultError(BaseConst.RETRUN_FAIL_CODE, msg);
    }

    public static R resultError(String code, String msg) {
        R r = new R();
        r.put(BaseConst.RETRUN_CODE, BaseConst.RETRUN_SUCCESS_CODE);
        r.put(BaseConst.RESULT_CODE, code);
        r.put(BaseConst.RETRUN_MSG, msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put(BaseConst.RESULT_CODE, BaseConst.RESULT_SUCCESS_CODE);
        r.put(BaseConst.RETRUN_MSG, msg);
        return r;
    }

    public static <T> R<T> ok(T t){
        R<T> r = new R<>();
        r.put(BaseConst.RESULT_CODE, BaseConst.RESULT_SUCCESS_CODE);
        r.put(BaseConst.RETRUN_MSG,t);
        return r;
    }

    @SuppressWarnings("unchecked")
    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    @Override
    public R put(String key, Object value){
        super.put(key,value);
        return this;
    }

    public static R ok() {
        return new R();
    }
}
