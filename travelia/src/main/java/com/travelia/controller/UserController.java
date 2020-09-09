package com.travelia.controller;


import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.response.CommonReturnType;
import com.travelia.service.UserService;
import com.travelia.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

    /**
     * 根据用户ID获取用户
     * @return 所有用户model
     * @throws BusinessException
     */
    @RequestMapping(value = "/getUserById", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getUserById(@RequestParam(name = "userId") Integer userId) throws BusinessException {
        UserModel userModel = userService.getUserByUserId(userId);
        return CommonReturnType.create(userModel, "success");
    }

    /**
     * 用户注册
     * @param telephone
     * @param password
     * @return
     */
    @RequestMapping(value = "/register", method = {RequestMethod.GET}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telephone") String telephone,
                                    @RequestParam(name = "password") String password) throws BusinessException {
        if (telephone == null || telephone.equals("")) {
            throw new BusinessException(BusinessError.USER_TELEPHONE_NOT_EMPTY);
        }
        UserModel userModel = new UserModel();
        userModel.setUserTelephone(telephone);
        userModel.setEncryptPassword(password);
        return CommonReturnType.create();
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/usernameSearch", method = {RequestMethod.GET})
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
        if (password == null || ("").equals(password)) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "密码不能为空");
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
