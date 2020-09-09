package com.travelia.controller;


import com.travelia.error.BusinessError;
import com.travelia.error.BusinessException;
import com.travelia.response.CommonReturnType;
import com.travelia.service.AdminService;
import com.travelia.service.model.AdminModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 登录校验
     * @param account
     * @param password
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "account") String account,
                                  @RequestParam(name = "password") String password) throws BusinessException {

        AdminModel adminModel = adminService.validateLogin(account, password);
        if (adminModel == null) {
            throw new BusinessException(BusinessError.USER_LOGIN_FAIL);
        }
        httpServletRequest.getSession().setAttribute("LOGIN", true);
        httpServletRequest.getSession().setAttribute("ADMIN", adminModel);

        return CommonReturnType.create();
    }

    /**
     * 主页
     * @return 返回管理员信息
     * @throws BusinessException
     */
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType index() throws BusinessException {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("LOGIN");
        AdminModel adminModel = (AdminModel) httpServletRequest.getSession().getAttribute("ADMIN");
        if (isLogin == null || !isLogin || adminModel == null) {
            throw new BusinessException(BusinessError.ADMIN_NOT_LOGIN);
        }
        return CommonReturnType.create(adminModel, "success");
    }

    /**
     * 退出登录
     * @return success
     */
    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType logout() {
        if(httpServletRequest.getSession().getAttribute("LOGIN") != null &&
                (Boolean) httpServletRequest.getSession().getAttribute("LOGIN")) {
            httpServletRequest.getSession().removeAttribute("LOGIN");
            httpServletRequest.getSession().removeAttribute("ADMIN");
        }
        return CommonReturnType.create();
    }


}
