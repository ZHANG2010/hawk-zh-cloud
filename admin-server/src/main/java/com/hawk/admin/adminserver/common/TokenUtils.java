package com.hawk.admin.adminserver.common;


import org.springframework.util.Base64Utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * TokenUtils
 * token生成工具
 * 第一部分：header 头部
 * 第二部分：payload 载荷
 * 第三部分：signature 签名
 * @author hawk_zhang
 * @date 2018/10/27
 */
public class TokenUtils {

    /**
     * 密钥
     */
    public static final String SECRET = "I Love You Baby";
    /**
     * 生成一个token
     * @return
     */
    public String creatToken() throws NoSuchAlgorithmException {
        //先建立一个头部 header
        Map<String,String> headerMap = new HashMap<>(2);
        //alg 签名
        headerMap.put("alg","HS256");
        //typ 类型
        headerMap.put("typ","JWT");

        //建立一个载荷 payload
        Map<String,String> payloadMap = new HashMap<>(3);
        //subject 主题
        payloadMap.put("sub","test token");
        //name 用户名
        payloadMap.put("name","Hawk Zhang");
        //iss: issuser 发行人(生成 JWT 的是谁)
        payloadMap.put("iss","Hawk Zhang");
        //exp: expiration 过期的时间
        payloadMap.put("exp","30");
        //admin: 管理员
        payloadMap.put("admin","true");

        //aud 接收方
        //iat 签发时间

        //header 加密
        byte[] headerBase64 = Base64Utils.encode(headerMap.toString().getBytes());
        //payload 载荷 加密
        byte[] payloadBase64 = Base64Utils.encode(payloadMap.toString().getBytes());

        String header_payload =headerBase64.toString() + "." + payloadBase64.toString();


        Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET.getBytes(), "HmacSHA256");
        try {
            hmacSHA256.init(secretKeySpec);
            byte[] bytes = hmacSHA256.doFinal(header_payload.getBytes());

            StringBuffer stringBuffer = new StringBuffer();
            String stmp;
            for (int n=0;bytes != null && n < bytes.length;n++){
                stmp = Integer.toHexString(bytes[n] & 0XFF);
                if (stmp.length() == 1){
                    stringBuffer.append('0');
                }
                stringBuffer.append(stmp);
            }
            return stringBuffer.toString().toLowerCase();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return "";
    }
}
