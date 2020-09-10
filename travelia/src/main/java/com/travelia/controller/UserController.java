package com.travelia.controller;


import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.response.CommonReturnType;
import com.travelia.service.UserService;
import com.travelia.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

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

    /**
     * 根据用户ID获取用户
     * @return 所有用户model
     * @throws BusinessException
     */
    @RequestMapping(value = "/getUserById", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getUserById(@RequestParam(name = "userId") Integer userId) throws BusinessException {
        UserModel userModel = userService.getUserByUserId(userId);
        if (userModel == null) {
            throw new BusinessException(BusinessError.USER_NOT_FOUND);
        }
        return CommonReturnType.create(userModel, "success");
    }

    /**
     * 用户登录
     * @param telephone
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws BusinessException
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telephone")String telephone,
                                  @RequestParam(name = "password") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        if (telephone == null || telephone.equals("")) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入手机号");
        }
        if (password == null || password.equals("")) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入密码");
        }
        UserModel userModel = userService.validateLogin(telephone, encodeByMD5(password));
        if (userModel == null) {
            throw new BusinessException(BusinessError.USER_LOGIN_FAIL);
        }
        httpServletRequest.getSession().setAttribute("USER_LOGIN", true);
        httpServletRequest.getSession().setAttribute("USER", userModel);

        return CommonReturnType.create(userModel);
    }

    /**
     * 检查是否有用户登录
     * @return
     */
    @RequestMapping(value = "/validateLogin", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType validateLogin() {
       Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("USER_LOGIN");
       UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("USER");
       if (isLogin == null || !isLogin || userModel == null) {
           return CommonReturnType.create(null, "fail");
       }
       return CommonReturnType.create(userModel);
    }


    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType logout() {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("USER_LOGIN");
        if (isLogin != null) {
            httpServletRequest.getSession().removeAttribute("USER_LOGIN");
        }
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("USER");
        if (userModel != null) {
            httpServletRequest.getSession().removeAttribute("USER");
        }
        return CommonReturnType.create();
    }

    /**
     * 用户注册
     * @param telephone
     * @param password
     * @return
     */
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telephone") String telephone,
                                     @RequestParam(name = "password") String password,
                                     @RequestParam(name = "username") String username) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (telephone == null || telephone.equals("")) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入手机号");
        }
        if (password == null || password.equals("")) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入密码");
        }
        UserModel userModel = new UserModel();
        if (username == null || username.equals("")) {
            userModel.setUsername("新用户");
        } else {
            userModel.setUsername(username);
        }
        userModel.setUserTelephone(telephone);
        userModel.setEncryptPassword(encodeByMD5(password));
        userService.addUser(userModel);
        return CommonReturnType.create();
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/usernameSearch", method = {RequestMethod.GET}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType searchUsersByUsername(@RequestParam(name = "username") String username) throws BusinessException {
        List<UserModel> userModelList = userService.getUsersLikeUsername(username);
        if (userModelList == null) {
            throw new BusinessException(BusinessError.USER_NOT_FOUND);
        }
        return CommonReturnType.create(userModelList, "success");
    }


    /**
     * 添加用户信息
     * @param username
     * @param password
     * @param telephone
     * @param gender
     * @param age
     * @param birthday
     * @param email
     * @return
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/addUser", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType addUser(@RequestParam(name = "username") String username,
                                    @RequestParam(name = "password") String password,
                                    @RequestParam(name = "telephone") String telephone,
                                    @RequestParam(name = "gender") Integer gender,
                                    @RequestParam(name = "age") Integer age,
                                    @RequestParam(name = "birthday") String birthday,
                                    @RequestParam(name = "email") String email) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        if (telephone == null || ("").equals(telephone)) {
            throw new BusinessException(BusinessError.USER_TELEPHONE_NOT_EMPTY);
        }
        if (password == null || ("").equals(password)) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "密码不能为空");
        }

        UserModel user = new UserModel();
        user.setUsername(username);
        user.setUserTelephone(telephone);
        user.setEncryptPassword(encodeByMD5(password));
        user.setAge(age);
        user.setGender(gender);
        user.setBirthday(birthday);
        user.setUserEmail(email);
        userService.addUser(user);

        return CommonReturnType.create();
    }

    @RequestMapping(value = "/updateUser", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType updateUser(@RequestParam(name = "userId" ) Integer userId,
                                    @RequestParam(name = "username") String username,
                                    @RequestParam(name = "password") String password,
                                    @RequestParam(name = "telephone") String telephone,
                                    @RequestParam(name = "gender") Integer gender,
                                    @RequestParam(name = "age") Integer age,
                                    @RequestParam(name = "birthday") String birthday,
                                    @RequestParam(name = "email") String email) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (userId == null || userId.equals("")) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "用户ID不能为空");
        }
        if (telephone == null || ("").equals(telephone)) {
            throw new BusinessException(BusinessError.USER_TELEPHONE_NOT_EMPTY);
        }
        UserModel userModel = userService.getUserByUserId(userId);
        if (userModel == null) {
            throw new BusinessException(BusinessError.USER_NOT_FOUND);
        }

        if (password == null || ("").equals(password)) {
            userModel.setEncryptPassword(userModel.getEncryptPassword());
        }

        UserModel user = new UserModel();
        user.setUserId(userId);
        user.setUsername(username);
        user.setUserTelephone(telephone);
        user.setEncryptPassword(encodeByMD5(password));
        user.setAge(age);
        user.setGender(gender);
        user.setBirthday(birthday);
        user.setUserEmail(email);
        userService.updateUser(user);

        return CommonReturnType.create();
    }

    @RequestMapping(value = "/deleteUser", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType deleteUser(@RequestParam(name = "userId") Integer userId) {
        userService.deleteUser(userId);
        return CommonReturnType.create();
    }

}
