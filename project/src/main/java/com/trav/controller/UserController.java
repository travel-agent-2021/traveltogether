package com.trav.controller;


import com.trav.error.BusinessException;
import com.trav.response.CommonReturnType;
import com.trav.service.UserService;
import com.trav.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;


    @RequestMapping(value="/login", method={RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name="username") String username,
                                  @RequestParam(name="password") String password) throws BusinessException {

        UserModel userModel = userService.validateLogin(username, password);
        if (userModel != null) {
            this.httpServletRequest.getSession().setAttribute("LOGIN", true);
            this.httpServletRequest.getSession().setAttribute("USER", userModel);
        }

        return CommonReturnType.create(null);
    }

    @RequestMapping(value="/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name="username") String username,
                                     @RequestParam(name="password") String password,
                                     @RequestParam(name="age") Integer age,
                                     @RequestParam(name="gender") Integer gender,
                                     @RequestParam(name="telephone") String telephone,
                                     @RequestParam(name="email") String email)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {

        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setAge(age);
        userModel.setGender(gender);
        userModel.setTelephone(telephone);
        userModel.setEncryptPassword(encodeByMD5(password));
        userModel.setEmail(email);

        userService.insertUser(userModel);
        return CommonReturnType.create(null);
    }


    public String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

}
