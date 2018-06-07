package com.leeves.utils.md5;

import java.security.MessageDigest;

/**
 * Description: 带盐值加密的MD5
 * Package: com.bxd.utils.sign
 *
 * @author Leeves
 * @version 1.0.0  2018-06-04
 */

public class MD5Util {

    private final static String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private final static String SALT = "BBXXDD";

    /**
     * 带盐MD5加密（默认盐值）
     * @param rawStr 明文
     * @return MD5密文
     */
    public static String encodeMD5WithSalt(String rawStr) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //加密后的字符串
            result = byteArrayToHexString(md.digest((rawStr + "bxd" + SALT).getBytes("utf-8")));
        } catch (Exception ignored) {

        }
        return result;
    }

    /**
     * 带盐MD5加密
     * @param rawStr 明文
     * @param salt 盐值
     * @return MD5密文
     */
    public static String encodeMD5WithSalt(String rawStr, String salt) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //加密后的字符串
            result = byteArrayToHexString(md.digest((rawStr + salt).getBytes("utf-8")));
        } catch (Exception ignored) {

        }
        return result;
    }

    /**
     * MD5带盐（默认盐值）校验
     * @param encStr MD5密文
     * @param rawStr 明文
     * @return boolean
     */
    public static boolean isMD5WithSaltValid(String encStr, String rawStr) {
        String pass1 = "" + encStr;
        String pass2 = encodeMD5WithSalt(rawStr, SALT);
        return pass1.equals(pass2);
    }

    /**
     * MD5带盐（默认盐值）校验
     * @param encStr MD5密文
     * @param rawStr 明文
     * @param salt 盐值
     * @return boolean
     */
    public static boolean isMD5WithSaltValid(String encStr, String rawStr, String salt) {
        String pass1 = "" + encStr;
        String pass2 = encodeMD5WithSalt(rawStr, salt);
        return pass1.equals(pass2);
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }



    public static Boolean isXmlSignatureValid(String str) {
        String dsignTagS = "<DSIGN>";
        String dsignTagE = "</DSIGN>";
        StringBuilder buff = new StringBuilder();
        int dsignTagStart = str.indexOf(dsignTagS);
        int dsignTagEnd = str.indexOf(dsignTagE) + dsignTagE.length();
        String addStart = str.substring(0, dsignTagStart);
        String addEnd = str.substring(dsignTagEnd, str.length());
        String signStr = str.substring(dsignTagStart + 7, dsignTagEnd - 8);
        buff.append(addStart).append(addEnd);
        String noDsignXmlStr = buff.toString();
        return MD5Util.isMD5WithSaltValid(signStr, noDsignXmlStr);
    }

    public static String signatureXmlStr(String str) {
        StringBuilder buff = new StringBuilder();
        String dsignTagS = "<DSIGN>";
        String dsignTagE = "</DSIGN>";
        String simgTagE = "</SIMSG>";
        String encodeMD5WithSalt = MD5Util.encodeMD5WithSalt(str);
        String signTag = dsignTagS + encodeMD5WithSalt + dsignTagE;
        int dsignTagEnd = str.indexOf(simgTagE);
        String addStart = str.substring(0, dsignTagEnd);
        String addEnd = str.substring(dsignTagEnd, str.length());
        buff.append(addStart).append(signTag).append(addEnd);
        return buff.toString();
    }

}
