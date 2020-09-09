package com.travelia.controller;

import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    /**
     * 异常处理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception e) {
        Map<String, Object> responseData = new HashMap<>();
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            responseData.put("errCode", businessException.getErrorCode());
            responseData.put("errMsg", businessException.getErrorMsg());
        } else {
            responseData.put("errCode", BusinessError.UNKNOWN_ERROR.getErrorCode());
            responseData.put("errMsg", BusinessError.UNKNOWN_ERROR.getErrorMsg());
            e.printStackTrace();
        }
        return CommonReturnType.create(responseData, "fail");
    }

    /**
     * 密码MD5加密
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String encryptStr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return encryptStr;
    }


}
