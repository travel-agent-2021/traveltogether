package com.travelia.controller;


import com.travelia.error.BusinessException;
import com.travelia.response.CommonReturnType;
import com.travelia.service.UserService;
import com.travelia.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    /**
     * 获取用户列表
     * @return 所有用户model
     * @throws BusinessException
     */
    @RequestMapping(value = "/getAllUsers", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getAllUsers() throws BusinessException {
        List<UserModel> users =  userService.getAllUsers();
        return CommonReturnType.create(users, "success");
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType register(@RequestParam(value = "username") String username,
                                    @RequestParam(value = "password") String password) {


        return CommonReturnType.create();
    }


}
