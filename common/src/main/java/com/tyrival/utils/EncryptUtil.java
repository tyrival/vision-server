package com.tyrival.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/27
 * @Version: V1.0
 */
public class EncryptUtil {

    public static final String KEY_SHA = "SHA";

    public static String handler(String inputStr) {
        BigInteger sha = null;
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sha.toString(32);
    }
}
