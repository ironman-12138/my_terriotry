package com.xtn.common.utils;


import com.xtn.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;
import java.util.Objects;

import static javax.crypto.Cipher.DECRYPT_MODE;

/**
 * AES工具类
 *
 * @author jiangjh
 */
@Slf4j
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String CHAR_SET = "UTF-8";

    /**
     * 算法/模式/补码方式
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String PKCS7_CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";

    /**
     * AES加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        if (StringUtil.isEmpty(content)) {
            return content;
        }
        if (StringUtil.isEmpty(password)) {
            throw new RuntimeException("AES encryption password can not be null or empty !");
        }
        try {
            //创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            SecretKeySpec spec = new SecretKeySpec(password.getBytes(CHAR_SET), KEY_ALGORITHM);
            //初始化为加密密码器
            cipher.init(Cipher.ENCRYPT_MODE, spec);
            byte[] byteContent = content.getBytes(CHAR_SET);
            byte[] encryptByte = cipher.doFinal(byteContent);
            return StringUtil.parseByte2HexStr(encryptByte);
        } catch (Exception e) {
            log.error("AES encryption operation has exception,content:{},password:{},exception:{}", content, password, e);
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }


    /**
     * AES解密操作
     *
     * @param encryptContent 加密的密文
     * @param password       解密的密钥
     * @return
     */
    public static String decrypt(String encryptContent, String password) {
        if (StringUtil.isEmpty(encryptContent)) {
            return encryptContent;
        }
        if (StringUtil.isEmpty(password)) {
            throw new RuntimeException("AES encryption password can not be null or empty !");
        }
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            SecretKeySpec spec = new SecretKeySpec(password.getBytes(CHAR_SET), KEY_ALGORITHM);
            //设置为解密模式
            cipher.init(DECRYPT_MODE, spec);
            //执行解密操作
            log.info("入参encryptContent的值是：[{}]，入参password的值是[{}]", encryptContent, password);
            byte[] result = cipher.doFinal(Objects.requireNonNull(StringUtil.parseHexStr2Byte(encryptContent)));
            return new String(result, CHAR_SET);
        } catch (Exception e) {
            log.error("AES decryption operation has exception,content:[{}],password:[{}],exception:[{}]", encryptContent, password, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析参数
     * 先解密,而后转化为map
     *
     * @param param
     * @param password
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map parse(String param, String password) {
        try {
            param = decrypt(param, password);
            return JsonUtil.parse(param, Map.class);
        } catch (Exception e) {
            log.error("parse param error, param:[{}]", param);
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析参数
     * 先解密而后转化为对应类型
     *
     * @param param
     * @param password
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parse(String param, String password, Class<T> clazz) {
        try {
            param = decrypt(param, password);
            return JsonUtil.parse(param, clazz);
        } catch (Exception e) {
            log.error("parse param error, param:[{}],clazz:[{}]", param, clazz.getName());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        String info = "张三-15600000000-120394199510221122-IDENTITY_CARD";
//        String encrypt = encrypt(info, "1234567qazwsxedc");
//        System.out.println(encrypt);
//        String decrypt = decrypt(encrypt, "1234567qazwsxedc");
//        System.out.println(decrypt);

        String qrcode = "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";
        byte[] bytes = Base64.decodeBase64(qrcode);
        String s = new String(bytes);
        System.out.println(s);
    }
}
