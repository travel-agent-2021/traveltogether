package com.trav.controller;


import com.trav.response.CommonReturnType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class UserController {

    @RequestMapping("/login")
    @ResponseBody
    public CommonReturnType login(@RequestParam(name="username") String username,
                                  @RequestParam(name="password") String password) {

        


        return CommonReturnType.create(null);
    }


}
