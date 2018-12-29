package com.dashu.log.client;


import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.MessageFormat;

/**
 * @Description
 * @Author: xuyouchang
 * @Date 2018/11/27 下午4:12
 **/
@Component
public class ClientUtil {

    /**
     * 数据返回格式构造
     * @param ret 返回码
     * @param errorMsg 错误信息
     * @param data 真实数据
     * @return
     */
    public JSONObject responseMessage(Integer ret,String errorMsg,Object data){
        JSONObject returnObject = new JSONObject();
        returnObject.put("ret",ret);
        returnObject.put("errorMsg",errorMsg);
        returnObject.put("datas","data");
        return returnObject;
    }

    /** 获取cookie **/
    public  String getCookie(HttpServletRequest request, String cookieName){

        Cookie[] cookies =  request.getCookies();

        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /** 写入cookie **/
    public void writeCookie(HttpServletResponse response, String cookieName, String value) throws Exception {
        value = getMD5Str(value);
        Cookie cookie = new Cookie(cookieName,value);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

    }

    /** md5加密 **/
    public static String getMD5Str(String str) throws Exception {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception("MD5加密出现错误，"+e.toString());
        }
    }



}
