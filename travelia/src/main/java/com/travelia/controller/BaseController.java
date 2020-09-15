package com.travelia.controller;

import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

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
        }  else if (e instanceof MethodArgumentTypeMismatchException) {
            responseData.put("errCode", BusinessError.PARAMETER_MISMATCH_ERROR.getErrorCode());
            responseData.put("errMsg", BusinessError.PARAMETER_MISMATCH_ERROR.getErrorMsg());
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

    /**
     * 获取当前日期
     * @param pattern
     * @return
     */
    public String getNowDate(String pattern) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
        return now;
    }

    /**
     * 获取当前日期前N天
     * @param before
     * @return
     */
    public String getBeforeDate(int before) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH, -(before));

        Date beforeDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return sdf.format(beforeDate);
    }


    /**
     * 生成10位随机ID
     * @return
     */
    public  Integer generateRandomId() {
        StringBuilder id = new StringBuilder();

        // 取年份后两位
        String now = getNowDate("yyyy");
        id.append(now.substring(now.length() - 2));

        // 取时间戳后六位
        String timeStr = String.valueOf(System.currentTimeMillis());
        id.append(timeStr.substring(timeStr.length() - 6));

        // 取随机两位数10-99
        id.append(new Random().nextInt(90) + 10);

        return Integer.parseInt(id.toString());
    }


}
