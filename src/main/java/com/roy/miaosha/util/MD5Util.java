package com.roy.miaosha.util;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5(String str){
        String md5Hex = DigestUtils.md5Hex(str);
        return md5Hex;
    }

    private static final String salt = "1a2b3c4d";


    public static String inputPassFormPass(String inputPass){

        //这里可以随便拼接密码和Salt
        return DigestUtils.md5Hex(inputPass + salt);
    }

    /**
     * 根据随机salt和formPass在次进行MD5
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String formPass, String salt){

        return DigestUtils.md5Hex(formPass + salt);
    }

    public static String inputPassToDbPass(String inputPass, String saltDB){
        String formPass = inputPassFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassFormPass("123456"));
        System.out.println(inputPassToDbPass("123456","1a2b3c4d"));
    }
}
