package com.travelia.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class UserController extends BaseController {



}
