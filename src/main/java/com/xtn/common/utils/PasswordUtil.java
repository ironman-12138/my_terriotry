package com.xtn.common.utils;

import org.apache.commons.codec.binary.Hex;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * @author xCoder
 */
public class PasswordUtil {

    private static final int SALT_LENGTH = 16; // 盐值长度

    /**
     * 生成随机盐值
     *
     * @return 盐值
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Hex.encodeHexString(salt);
    }

    /**
     * 对密码进行加盐加密
     *
     * @param password 明文密码
     * @param salt     盐值
     * @return 加密后的密码
     */
    public static String encryptPassword(String password, String salt) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 校验密码是否匹配
     *
     * @param password     明文密码
     * @param salt         盐值
     * @param encryptedPwd 加密后的密码
     * @return 是否匹配
     */
    public static boolean checkPassword(String password, String salt, String encryptedPwd) {
        String hash = encryptPassword(password, salt);
        return hash.equals(encryptedPwd);
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("123456", "381f115004ee86e5c549f7d5a5c7618a"));
        System.out.println(checkPassword("123456", "381f115004ee86e5c549f7d5a5c7618a", "fed559212735e82f2ccb89dfbce7e006"));
    }
}
