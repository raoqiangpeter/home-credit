package com.raoqiang.homecredit.utils;

import java.util.Random;

/**
 * 随机生成字符串
 */
public class RandomStr {

    final static char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 生产指定长度的字符串
     * @param length
     * @return String
     */
    public static String randomString(int length){
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(CHARS[random.nextInt(62)]);
        }
        return buffer.toString();
    }
}
