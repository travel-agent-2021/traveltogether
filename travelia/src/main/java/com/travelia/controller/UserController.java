package com.travelia.controller;


import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.response.CommonReturnType;
import com.travelia.service.UserService;
import com.travelia.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.attribute.FileAttributeView;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    /**
     * 退出登录
     * @return
     */
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
     * 根据用户名查找用户
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/getAgeData", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getAgeCount() throws BusinessException {
        Map<String, Integer> ageMap = new TreeMap<>();
        int count = userService.getCountByAge(0, 18);
        ageMap.put("under 18", count);
        count = userService.getCountByAge(18, 35);
        ageMap.put("18-35", count);
        count = userService.getCountByAge(35, 60);
        ageMap.put("35-60", count);
        count = userService.getCountByAge(60, 1000);
        ageMap.put("above 60", count);
        return CommonReturnType.create(ageMap);
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
     * @param userImageSource
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
                                    @RequestParam(name = "email") String email,
                                    @RequestParam(name = "userImageSource") String userImageSource) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

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
        user.setUserImageSource(userImageSource);
        userService.addUser(user);

        return CommonReturnType.create();
    }

    /**
     * 更新用户
     * @param userId
     * @param username
     * @param password
     * @param telephone
     * @param gender
     * @param birthday
     * @param email
     * @param userImageSource
     * @return
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/updateUser", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType updateUser(@RequestParam(name = "userId" ) Integer userId,
                                    @RequestParam(name = "username") String username,
                                    @RequestParam(name = "password") String password,
                                    @RequestParam(name = "telephone") String telephone,
                                    @RequestParam(name = "gender") Integer gender,
                                   // @RequestParam(name = "age") Integer age,
                                    @RequestParam(name = "birthday") String birthday,
                                    @RequestParam(name = "email") String email,
                                    @RequestParam(name = "userImageSource") String userImageSource) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (userId == null || userId.equals("")) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "用户ID不能为空");
        }
        if (telephone == null || ("").equals(telephone)) {
            throw new BusinessException(BusinessError.USER_TELEPHONE_NOT_EMPTY);
        }
        UserModel user = userService.getUserByUserId(userId);
        if (user == null) {
            throw new BusinessException(BusinessError.USER_NOT_FOUND);
        }

        if (password == null || ("").equals(password)) {
            user.setEncryptPassword(user.getEncryptPassword());
        } else {
            user.setEncryptPassword(encodeByMD5(password));
        }

        user.setUserId(userId);
        user.setUsername(username);
        user.setUserTelephone(telephone);
        user.setAge(getAge(birthday));
        user.setGender(gender);
        user.setBirthday(birthday);
        user.setUserEmail(email);
        user.setUserImageSource(userImageSource);
        userService.updateUser(user);

        return CommonReturnType.create();
    }

    /**
     * 更新密码
     * @param userId
     * @param password
     * @param newPassword
     * @return
     * @throws BusinessException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/updatePassword", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType updatePassword(@RequestParam(name = "userId") Integer userId,
                                           @RequestParam(name = "password") String password,
                                           @RequestParam(name = "newPassword") String newPassword) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (userId == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "用户ID不能为空");
        }
        if (password == null || password.equals("")) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入密码");
        }
        if (newPassword == null || newPassword.equals("")) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请输入新密码");
        }

        UserModel user = userService.getUserByUserId(userId);
        if (user == null) {
            throw new BusinessException(BusinessError.USER_NOT_FOUND);
        }

        if (!user.getEncryptPassword().equals(encodeByMD5(password))) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "密码错误, 请重试");
        }

        if (newPassword.equals(password)) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "新密码不能和原密码一样噢！");
        }

        user.setEncryptPassword(encodeByMD5(newPassword));
        userService.updateUser(user);
        httpServletRequest.getSession().removeAttribute("USER_LOGIN");
        httpServletRequest.getSession().removeAttribute("USER");
        return CommonReturnType.create();
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deleteUser", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType deleteUser(@RequestParam(name = "userId") Integer userId) {
        userService.deleteUser(userId);
        return CommonReturnType.create();
    }


    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType upload(MultipartRequest request) throws BusinessException {
        MultipartFile file = request.getFile("imageFile");
        if (file == null) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "请选择图片！");
        }
        String filename = file.getOriginalFilename();
        String suffixName = filename.substring(filename.lastIndexOf("."));
        filename = UUID.randomUUID() + suffixName;
        String filepath = "C:\\Users\\casdg\\Desktop\\traveltogether\\travelia\\src\\main\\resources\\backstage\\img";
        try {
            file.transferTo(new File(filepath + filename));
            return CommonReturnType.create(filename + filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonReturnType.create(null, "fail");
    }

    /**
     * 根据生日计算年龄
     * @param birthday
     * @return
     * @throws BusinessException
     */
    private static Integer getAge(String birthday) throws BusinessException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date formatDate = null;
        try {
            formatDate = sdf.parse(birthday);
        } catch (ParseException e) {
           throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "生日格式有误！");
        }
        Calendar cal = Calendar.getInstance();
        //出生日期晚于当前时间，无法计算
        if (cal.before(formatDate)) {
            throw new BusinessException(BusinessError.PARAMETER_VALIDATION_ERROR, "生日超过当前日期！");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(formatDate);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age;
    }


    public static void main(String[] args) throws BusinessException {
        System.out.println(getAge("2022-12-31"));
    }

}
