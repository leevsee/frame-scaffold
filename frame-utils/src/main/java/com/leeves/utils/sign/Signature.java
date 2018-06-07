package com.leeves.utils.sign;

import com.leeves.utils.Jab2MapUtils;
import com.leeves.utils.md5.MD5Util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 15:23
 */
public class Signature {

    /**
     * 签名算法
     *
     * @param o 要参与签名的数据对象
     * @return 签名
     */
    public static String getSign(String key, Object o) throws IllegalAccessException {
        ArrayList<String> list = new ArrayList<String>();
        Class cls = o.getClass();

        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);

            if (f.get(o) != null && !(f.get(o).toString()).equals("") && !f.getName().equals("sign")) {
                list.add(f.getName() + "=" + f.get(o) + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();

        result += "key=" + key;

        //日志输出
        /*Util.log("Sign Before MD5:" + result);*/
        result = MD5Util.encodeMD5WithSalt(result).toUpperCase();
        /*Util.log("Sign Result:" + result);*/
        return result;
    }

    public static String getSign(String key, Map<String, Object> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null && !(entry.getValue().toString()).equals("") && !entry.getKey().equals("sign")) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        //WXUtil.log("Sign Before MD5:" + result);
        result = MD5Util.encodeMD5WithSalt(result).toUpperCase();
        //WXUtil.log("Sign Result:" + result);
        return result;
    }

    /**
     * 判断签名是否正确
     *
     * @param key 密钥
     * @param o 数据对象
     * @return 签名是否正确
     * @throws IllegalAccessException
     * @author leeves 2018.1.17
     */
    public static boolean isSignatureValid(String key, Object o) throws IllegalAccessException {
        Map<String, Object> data = Jab2MapUtils.beanToMap(o);
        return data.containsKey("sign") && getSign(key, o).equals(data.get("sign"));
    }

}
