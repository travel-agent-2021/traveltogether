package com.trav.controller;


import com.trav.error.BusinessException;
import com.trav.response.CommonReturnType;
import com.trav.service.UserService;
import com.trav.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

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


}
